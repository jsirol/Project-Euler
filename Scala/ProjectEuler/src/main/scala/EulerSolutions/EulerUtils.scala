package EulerSolutions

import scala.annotation.tailrec

object EulerUtils {

  // computes factorial of n
  def factorial(n: Int): BigInt = {
    @tailrec
    def factorial_iter(acc: BigInt, n: Int): BigInt = {
      if (n == 1) acc else factorial_iter(n * acc, n - 1)
    }
    if (n < 0) throw new IllegalArgumentException
    else if (n == 0) 1
    else factorial_iter(1, n)
  }

  def fibonacci(n: Long): BigInt = {
    val goldenRatio = (1 + math.sqrt(5)) / 2
    math.floor(math.pow(goldenRatio.toDouble, n) / math.sqrt(5) + 0.5).toLong
  }

  // computes binomial coefficient nCk
  def binomial(n: Int, k: Int): BigInt = {
    if (k < 0 | k > n) throw new IllegalArgumentException
    (BigInt(k+1) to BigInt(n)).product / factorial(k)
  }

  // naive implementation
  def divisors(n: Long): Set[Long] = {
    Set(n) ++ (1L to n / 2).filter(n % _ == 0)
  }

  // faster implementation for large numbers (based on prime factorization)
  def fastDivisors(n: Long, precomputedPrimesUpToN: Option[Array[Long]] = None): Set[Long] = {

    @tailrec
    def divisorsIter(divisors: Set[Long], usable: List[Long]): Set[Long] = {
      if (usable.isEmpty) divisors
      else divisorsIter(Set(usable.head) ++ divisors.map(x => usable.head * x) ++ divisors, usable.tail)
    }

    precomputedPrimesUpToN match {
      case None => Set(1L) ++ divisorsIter(Set(), primeFactors(n).flatMap(x => List.fill(x._2)(x._1)))
      case Some(s) => Set(1L) ++ divisorsIter(Set(), primeFactorsWithPrecomputedPrimes(n, s).flatMap(x => List.fill(x._2)(x._1)))
    }
  }

  // test if a number is prime
  def isPrime(n: Long): Boolean = {
    if (n == 1) false
    else if (n == 2) true
    else if (n % 2 == 0) false
    else {
      !(3L to (math.sqrt(n).ceil.toLong, 2L)).exists(n % _ == 0)
    }
  }

  def fastIsPrime(n: Long): Boolean = {
    // using wheel factorization with primes 2,3,5 utilized
    val skipList = List(4, 2, 4, 2, 4, 6, 2, 6) // how many we skip at each iteration

    @tailrec
    def fastIsPrimeIter(nCurrent: Long, skipListIdx: Int): Boolean = {
      if (nCurrent * nCurrent > n) true
      else if (n % nCurrent == 0) false
      else fastIsPrimeIter(nCurrent + skipList(skipListIdx), (skipListIdx + 1) % skipList.length)
    }

    if (n == 1) false
    else if (n == 2 || n == 3 || n == 5) true
    else if (List(2, 3, 5).exists(n % _ == 0)) false
    else {
      fastIsPrimeIter(7, 0)
    }
  }

  // returns the multiplicity of divisor d of n, e.g. multiplicity(25, 5) = 2, since 25=5*5
  // multiplicity(18, 3) = 2, since 18 = 3 * 3 * 2, multiplicity(18, 6) = 1 as 18 = 6*3
  def multiplicity(n: Long, d: Long): Int = {
    @tailrec
    def multiplicityIter(currentN: Long, currentMultiplicity: Int): Int = {
      if (currentN % d != 0) currentMultiplicity
      else multiplicityIter(currentN / d, currentMultiplicity+1)
    }
    multiplicityIter(n, 0)
  }

  // returns a set of prime factors of n
  def primeFactors(n: Long): List[(Long, Int)] = {
    // using wheel factorization with primes 2,3,5 utilized
    val skipList = List(4, 2, 4, 2, 4, 6, 2, 6) // how many we skip at each iteration

    @tailrec
    def primesIter(factors: List[(Long, Int)], nRemaining: Long, current: Long, skipIdx: Int): List[(Long, Int)] = {
      if (nRemaining == 1) return factors

      val currentMultiplicity = multiplicity(nRemaining, current)
      if (currentMultiplicity == 0) primesIter(factors, nRemaining, current + skipList(skipIdx), (skipIdx+1) % skipList.length)
      else {
        primesIter(factors ++ List((current, currentMultiplicity)),
          nRemaining / math.pow(current, currentMultiplicity).toLong, current + skipList(skipIdx), (skipIdx+1) % skipList.length)
      }
    }

    val baseFactors = List(2L, 3L, 5L).map(d => (d, multiplicity(n, d))).filter(x => x._2 > 0)
    val nRemaining = baseFactors.foldLeft(n)((x, f) => if (f._2 > 0) x / math.pow(f._1, f._2).toLong else x)
    primesIter(baseFactors, nRemaining, 7, skipIdx = 0)
  }

/*   Returns a set of prime factors of n (uses precomputed Array of primes up to n to speed up the factorization).
      Useful if multiple numbers up to n need to be factored.
*/
  def primeFactorsWithPrecomputedPrimes(n: Long, precomputedPrimesUpToN: Array[Long]): List[(Long, Int)] = {

    @tailrec
    def primesIter(factors: List[(Long, Int)], nRemaining: Long, currentPrimeIdx: Int): List[(Long, Int)] = {
      if (nRemaining == 1) return factors

      val currentMultiplicity = multiplicity(nRemaining, precomputedPrimesUpToN(currentPrimeIdx))
      if (currentMultiplicity == 0) primesIter(factors, nRemaining, currentPrimeIdx+1)
      else {
        primesIter(factors ++ List((precomputedPrimesUpToN(currentPrimeIdx), currentMultiplicity)),
          nRemaining / math.pow(precomputedPrimesUpToN(currentPrimeIdx), currentMultiplicity).toLong, currentPrimeIdx+1)
      }
    }
    primesIter(List(), n, 0)
  }

  // using formula divisors(n) = prod (a_i+1), where a_i is the multiplicity of the ith prime factor of n
  def numDivisors(n: Long): Long = {
    primeFactors(n).foldLeft(1L)((acc, p) => acc * (p._2+1))
  }

}
