package EulerSolutions

/*
Let d(n) be defined as the sum of proper divisors of n (numbers less than n which divide evenly into n).
If d(a) = b and d(b) = a, where a ≠ b, then a and b are an amicable pair and each of a and b are called amicable numbers.

For example, the proper divisors of 220 are 1, 2, 4, 5, 10, 11, 20, 22, 44, 55 and 110; therefore d(220) = 284. The proper divisors of 284 are 1, 2, 4, 71 and 142; so d(284) = 220.

Evaluate the sum of all the amicable numbers under 10000.
 */

import EulerUtils.fastDivisors

object PE_021 extends App {

  def isAmicableNumber(a: Long): Boolean = {
    val b = fastDivisors(a).sum - a // fast divisors include a so need to subtract a
    if (a != b) a == (fastDivisors(b).sum - b) // subtract b for same reason as above
    else false
  }

  val t0 = System.nanoTime()
  val result = (2 until 10000).foldLeft(0)((s, x) => if (isAmicableNumber(x)) s + x else s)
  println("Result: " + result) // 31626
  println("Elapsed time: " + (System.nanoTime() - t0) / 1e9 + "s")
}
