/*
The sequence of triangle numbers is generated by adding the natural numbers. So the 7th triangle number would be
1 + 2 + 3 + 4 + 5 + 6 + 7 = 28. The first ten terms would be:

  1, 3, 6, 10, 15, 21, 28, 36, 45, 55, ...

Let us list the factors of the first seven triangle numbers:

1: 1
3: 1,3
6: 1,2,3,6
10: 1,2,5,10
15: 1,3,5,15
21: 1,3,7,21
28: 1,2,4,7,14,28
We can see that 28 is the first triangle number to have over five divisors.

What is the value of the first triangle number to have over five hundred divisors?
*/

package EulerSolutions

import EulerUtils.numDivisors

import scala.annotation.tailrec

object PE_012 extends App {

  def firstTriangularNumberWithNumDivisors(targetNumDivisors: Int): Long = {
    @tailrec
    def triangularIter(currentNumber: Long, nextIndex: Long): Long = {
      if (numDivisors(currentNumber) >= targetNumDivisors) currentNumber
      else triangularIter(currentNumber+nextIndex, nextIndex+1)
    }
    triangularIter(1,2)
  }


  val t0 = System.nanoTime()
  val result = firstTriangularNumberWithNumDivisors(501)
  println("Result: " + result) // 76576500
  println("Elapsed time: " + (System.nanoTime() - t0) / 1e9 + "s")
}