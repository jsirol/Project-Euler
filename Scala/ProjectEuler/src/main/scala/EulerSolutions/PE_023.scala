package EulerSolutions

/*
A perfect number is a number for which the sum of its proper divisors is exactly equal to the number.
For example, the sum of the proper divisors of 28 would be 1 + 2 + 4 + 7 + 14 = 28, which means that 28
is a perfect number.

A number n is called deficient if the sum of its proper divisors is less than n and it is called abundant if this
sum exceeds n.

As 12 is the smallest abundant number, 1 + 2 + 3 + 4 + 6 = 16, the smallest number that can be written as
the sum of two abundant numbers is 24. By mathematical analysis, it can be shown that all integers greater than 28123
can be written as the sum of two abundant numbers. However, this upper limit cannot be reduced any further by analysis
even though it is known that the greatest number that cannot be expressed as the sum of two abundant numbers
is less than this limit.

Find the sum of all the positive integers which cannot be written as the sum of two abundant numbers.
*/

import EulerUtils.divisors

object PE_023 extends App {
  val t0 = System.nanoTime()

  val lowerBound = 28123
  val abundantNumbersBelowLowerBound = (1 to lowerBound).par.filter(x => divisors(x).sum > x).toSet
  val sumOfIntegersNotSumOfTwoAbundantNumbers = (1 to lowerBound).
    par.filter(x => !abundantNumbersBelowLowerBound.exists(y => abundantNumbersBelowLowerBound.contains(x - y))).sum

  println("Result: " + sumOfIntegersNotSumOfTwoAbundantNumbers) // 4179871
  println("Elapsed time: " + (System.nanoTime() - t0) / 1e9 + "s")
}
