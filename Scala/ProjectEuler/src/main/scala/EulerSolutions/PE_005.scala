package EulerSolutions

/* 2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.
What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?
 */

import scala.annotation.tailrec

object PE_005 extends App {

  def evenlyDivisible(n: Int, bySmallest: Int, byLargest: Int): Int = {

    @tailrec
    def evenlyDivisibleIter(n: Int): Int = {
      if ((bySmallest to byLargest).forall(x => n % x == 0)) n
      else evenlyDivisibleIter(n+1)
    }
    evenlyDivisibleIter(n)
  }

  val t0 = System.nanoTime()
  print("Result: " + evenlyDivisible(1, 2, 20))
  println("\nElapsed time: " + (System.nanoTime() - t0) / 1e9 + "s")
}
