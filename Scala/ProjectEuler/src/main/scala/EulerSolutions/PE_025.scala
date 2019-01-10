package EulerSolutions

import scala.annotation.tailrec

/*
The Fibonacci sequence is defined by the recurrence relation:

Fn = Fn−1 + Fn−2, where F1 = 1 and F2 = 1.
Hence the first 12 terms will be:

F1 = 1
F2 = 1
F3 = 2
F4 = 3
F5 = 5
F6 = 8
F7 = 13
F8 = 21
F9 = 34
F10 = 55
F11 = 89
F12 = 144
The 12th term, F12, is the first term to contain three digits.

What is the index of the first term in the Fibonacci sequence to contain 1000 digits?
 */

object PE_025 extends App {

  def firstFiboIndexWithDigits(numDigits: Int): Int = {
    @tailrec
    def fiboIter(previous: BigInt, previous2: BigInt, currentIndex: Int): Int = {
      val current = previous + previous2
      if (current.toString().split("").length == numDigits)
        currentIndex
      else
        fiboIter(current, previous, currentIndex+1)
    }
    if (numDigits == 1) 1
    else fiboIter(1, 1, 3)
  }


  val t0 = System.nanoTime()
  println("Result: " + firstFiboIndexWithDigits(1000))
  println("Elapsed time: " + (System.nanoTime() - t0) / 1e9 + "s")
}


