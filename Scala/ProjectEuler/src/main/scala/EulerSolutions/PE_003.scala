package EulerSolutions

/* The prime factors of 13195 are 5, 7, 13 and 29.
What is the largest prime factor of the number 600851475143 ?
 */

import EulerUtils.primeFactors

object PE_003 extends App {
  val t0 = System.nanoTime()
  println("Result: " + primeFactors(600851475143L).foldLeft(2L)((m, x) => math.max(m, x._1))) // 6857
  println("\nElapsed time: " + (System.nanoTime() - t0) / 1e9 + "s")
}
