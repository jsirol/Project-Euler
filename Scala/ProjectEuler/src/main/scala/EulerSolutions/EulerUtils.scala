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

  // computes binomial coefficien nCk
  def binomial(n: Int, k: Int): BigInt = {
    if (k < 0 | k > n) throw new IllegalArgumentException
    (BigInt(k+1) to BigInt(n)).product / factorial(k)
  }

  def divisors(n: Long): Set[Long] = {
    (1L to n / 2).filter(n % _ == 0).toSet
  }

  // test if a number is prime
  def isPrime(n: Long): Boolean = {
    if (n == 2) true
    else {
      !(3L to(math.sqrt(n).floor.toLong, 2L)).exists(n % _ == 0)
    }
  }

  // returns a set of prime factors of n
  def primeFactors(n: Long): Set[Long] = {
    @tailrec
    def primesIter(factors: Set[Long], n: Long, current: Long): Set[Long] = {
      if (n % current == 0) primesIter(factors + current, n / current.toLong, current)
      else if (current < n) primesIter(factors, n, current+2)
      else factors
    }
    if (n % 2 == 0) primesIter(Set(2), n / 2, 3)
    else primesIter(Set(), n, 3)
  }
}
