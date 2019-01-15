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
  def fastDivisors(n: Long): Set[Long] = {
    @tailrec
    def divisorsIter(divisors: Set[Long], usable: List[Long]): Set[Long] = {
      if (usable.isEmpty) divisors
      else divisorsIter(Set(usable.head) ++ divisors.map(x => usable.head * x) ++ divisors, usable.tail)
    }
    //1 :: divisorsIter(List(), primeFactors(n).toList.flatMap(x => (1 to x._2).map(y => math.pow(x._1, y).toLong)))
    Set(1L) ++ divisorsIter(Set(), primeFactors(n).toList.flatMap(x => List.fill(x._2)(x._1)))
  }

  // test if a number is prime
  def isPrime(n: Long): Boolean = {
    if (n == 2) true
    else {
      !(3L to(math.sqrt(n).floor.toLong, 2L)).exists(n % _ == 0)
    }
  }

  // returns a set of prime factors of n
  def primeFactors(n: Long): Set[(Long, Int)] = {

    def multiplicity(n: Long, p: Long): Int = {
      (1L to math.floor(math.sqrt(n)).toLong).takeWhile(candidate => n % math.pow(p, candidate) == 0).max.toInt
    }

    @tailrec
    def primesIter(factors: Set[(Long, Int)], nRemaining: Long, current: Long): Set[(Long, Int)] = {
      if (nRemaining % current == 0)
        primesIter(factors ++ Set((current, multiplicity(n, current))), nRemaining / current.toLong, current)
      else if (current < nRemaining)
        primesIter(factors, nRemaining, current+2)
      else factors
    }
    if (n % 2 == 0) primesIter(Set((2, multiplicity(n, 2))), n / 2, 3)
    else primesIter(Set(), n, 3)
  }

  // using formula divisors(n) = prod (a_i+1), where a_i is the multiplicity of the ith prime factor of n
  def numDivisors(n: Long): Long = {
    primeFactors(n).foldLeft(1L)((acc, p) => acc * (p._2+1))
  }

}
