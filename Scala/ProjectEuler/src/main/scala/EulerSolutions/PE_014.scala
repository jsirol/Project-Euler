package EulerSolutions

import scala.annotation.tailrec

/*
The following iterative sequence is defined for the set of positive integers:

n → n/2 (n is even)
n → 3n + 1 (n is odd)

Using the rule above and starting with 13, we generate the following sequence:

13 → 40 → 20 → 10 → 5 → 16 → 8 → 4 → 2 → 1
It can be seen that this sequence (starting at 13 and finishing at 1) contains 10 terms. Although it has not been
proved yet (Collatz Problem), it is thought that all starting numbers finish at 1.

Which starting number, under one million, produces the longest chain?

NOTE: Once the chain starts the terms are allowed to go above one million.
 */

object PE_014 extends App {

  def collatzLength(start: Int): Long = {
    @tailrec
    def collatzIter(n: Long, seqLength: Long): Long = {
      if (n == 1) seqLength
      else if (n % 2 == 0) collatzIter(n/2, seqLength+1)
      else collatzIter(3*n + 1, seqLength+1)
    }
    collatzIter(start, 1)
  }

  val t0 = System.nanoTime()
  val million = 1000 * 1000
  val result = (1 until million).par.map(x => (collatzLength(x), x)).reduce((x, y) => if (x._1 > y._1) x else y)._2
  println("Result: " + result) // 837799
  println("Elapsed time: " + (System.nanoTime() - t0) / 1e9 + "s")
}
