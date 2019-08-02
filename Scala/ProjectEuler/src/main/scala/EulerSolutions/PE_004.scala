package EulerSolutions

/* The prime factors of 13195 are 5, 7, 13 and 29.
What is the largest prime factor of the number 600851475143 ?
 */

import EulerUtils.primeFactors

object PE_004 extends App {
  val t0 = System.nanoTime()
  println("Result: " + primeFactors(600851475143L).
    foldLeft(1L)((p, x) => if (x._2 == 1) math.max(p, x._1) else p)) // 6857
  println("\nElapsed time: " + (System.nanoTime() - t0) / 1e9 + "s")
}

