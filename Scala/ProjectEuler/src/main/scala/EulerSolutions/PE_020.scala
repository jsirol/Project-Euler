package EulerSolutions

/*
n! means n × (n − 1) × ... × 3 × 2 × 1
For example, 10! = 10 × 9 × ... × 3 × 2 × 1 = 3628800,
and the sum of the digits in the number 10! is 3 + 6 + 2 + 8 + 8 + 0 + 0 = 27.
Find the sum of the digits in the number 100!
 */

import EulerUtils.factorial

object PE_020 extends App {
  val t0 = System.nanoTime()
  val factorialHundred = factorial(100).toString()
  println("Result: " + factorialHundred.split("").foldLeft(0)((digitSum, s) => digitSum + s.toInt))
  println("Elapsed time: " + (System.nanoTime() - t0) / 1e9 + "s")
}
