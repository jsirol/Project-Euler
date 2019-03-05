package EulerSolutions

import scala.annotation.tailrec
import EulerUtils.factorial

/*
The number, 1406357289, is a 0 to 9 pandigital number because it is made up of each of the digits 0 to 9 in some order,
but it also has a rather interesting sub-string divisibility property.

Let d1 be the 1st digit, d2 be the 2nd digit, and so on. In this way, we note the following:

d2d3d4=406 is divisible by 2
d3d4d5=063 is divisible by 3
d4d5d6=635 is divisible by 5
d5d6d7=357 is divisible by 7
d6d7d8=572 is divisible by 11
d7d8d9=728 is divisible by 13
d8d9d10=289 is divisible by 17
Find the sum of all 0 to 9 pandigital numbers with this property.
 */

object PE_043 extends App {

  // helper to evaluate if the pandigital substring divisibility condition holds
  // returns the number if so, else 0
  def pandigitalConditionHolds(x: String): Long = {
    if (x.length != 10)
      throw new IllegalArgumentException("number to check is not of length 10")
    val divisorsList = List(2, 3, 5, 7, 11, 13, 17)
    if (divisorsList.indices.forall(i => x.slice(i+1, i+4).toInt % divisorsList(i) == 0)) x.toLong
    else 0
  }

  // not as efficient solution as with using the available permutations iterator, but works...
  // this is using the lexicographic permutations problem PE_024 method for getting the nth lexicographic permutation
  def solve(values: List[Int]): Long = {
    @tailrec
    def check(remainder: Int, k: Int, solution: List[Int], left: List[Int]): List[Int] = {
      if (remainder == 0)
        solution ::: left
      else {
        val permutations = factorial(k).toInt
        val times = remainder / permutations
        if (times * permutations == remainder) {
          // need to reverse the left list, because if we are at solution, the rest has to be in "last" lexicographic order
          // for same reason, we pick (times-1)th element instead of element 'times'
          check(remainder - times * permutations, k-1, solution ::: List(left(times-1)), (left.take(times-1) ::: left.drop(times)).reverse)
        }
        else
          check(remainder - times * permutations, k-1, solution ::: List(left(times)), left.take(times) ::: left.drop(times+1))
      }
    }
    (1 to factorial(10).intValue).
      par.map(x => check(x, 9, List(), List.range(0, 10, 1)).mkString("")).
      foldLeft(0L)((s, x) => s + pandigitalConditionHolds(x))
  }


  val t0 = System.nanoTime()
  val numbers = List(0,1,2,3,4,5,6,7,8,9)
  val result = numbers.permutations.foldLeft(0L)((s, x) => s + pandigitalConditionHolds(x.mkString("")))
  //val result = solve(numbers)
  println("Result: " + result) // 16695334890
  println("Elapsed time: " + (System.nanoTime() - t0 ) / 1e9 + "s")
}
