package EulerSolutions

import EulerUtils.{fastDivisors, fastIsPrime}
/* Consider the divisors of 30: 1,2,3,5,6,10,15,30.
It can be seen that for every divisor d of 30, d+30/d is prime.

Find the sum of all positive integers n not exceeding 100 000 000
such that for every divisor d of n, d+n/d is prime. */

object PE_357 extends App {

  val t0 = System.nanoTime()
  val nMax = 100e6.toLong

  val primes = (List(2L) ++ (3L to (nMax+1, 2)).par.filter(x => fastIsPrime(x))).toArray
  println("Precomputed primes up to N")
  println("Elapsed time: " + (System.nanoTime() - t0) / 1e9 + "s")

  val primesSet = primes.toSet
  println("Created primes set")
  println("Elapsed time: " + (System.nanoTime() - t0) / 1e9 + "s")

/*   Only need to check
   1. even numbers n for which 2 + n / 2 is prime
   2. numbers n for which n+1 is prime, since d+n/d has to be prime for d=1
   */
  val result = primes.
    par.filter(n => primesSet.contains(2 + (n-1) / 2) &&
                    fastDivisors(n-1, Some(primes)).map(d => d + (n-1)/d).forall(p => primesSet.contains(p))).sum + 1


  println("Result: " + result) // 1739023853137
	println("Elapsed time: " + (System.nanoTime() - t0) / 1e9 + "s")
}
