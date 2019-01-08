package EulerSolutions
/*
The sum of the squares of the first ten natural numbers is,
1^2 + 2^2 + ... + 10^2 = 385
The square of the sum of the first ten natural numbers is,
(1 + 2 + ... + 10)^2 = 55^2 = 3025
Hence the difference between the sum of the squares of the first ten natural numbers and the square of the sum is
3025 − 385 = 2640.
Find the difference between the sum of the squares of the first one hundred natural numbers and the square of the sum.
*/

object PE_006 extends App {

  val t0 = System.nanoTime()
  val sumOfSquares = (1L to 100L).foldLeft(0L)((s, x) => s + x*x)
  val sum = (1L to 100L).sum

  print("Result: " + (sum*sum - sumOfSquares))
  println("\nElapsed time: " + (System.nanoTime() - t0) / 1e9 + "s")
}
