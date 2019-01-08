package EulerSolutions
/*
By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th prime is 13.

What is the 10 001st prime number?
*/

import EulerUtils.isPrime

import scala.annotation.tailrec

object PE_007 extends App {


  def nthPrime(n: Long): Long = {
    @tailrec
    def nthPrimeIter(test: Long, idx: Long): Long = {
      if (idx+1 == n && isPrime(test)) test
      else if (isPrime(test)) nthPrimeIter(test+2, idx+1)
      else nthPrimeIter(test+2, idx)
    }
    nthPrimeIter(3,1)
  }

  val t0 = System.nanoTime()
  print("Result: " + nthPrime(10001))
  println("\nElapsed time: " + (System.nanoTime() - t0) / 1e9 + "s")
}
