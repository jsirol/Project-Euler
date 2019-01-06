package EulerSolutions

/* The prime factors of 13195 are 5, 7, 13 and 29.
What is the largest prime factor of the number 600851475143 ?
 */

import EulerUtils.isPrime

object PE_004 extends App {
  val t0 = System.nanoTime()
  print("Result: " +isPrime(1999L))
  println("\nElapsed time: " + (System.nanoTime() - t0) / 1e9 + "s")
}

