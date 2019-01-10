package EulerSolutions

/*
  A permutation is an ordered arrangement of objects. For example, 3124 is one possible permutation of the digits
  1, 2, 3 and 4. If all of the permutations are listed numerically or alphabetically, we call it lexicographic order.
  The lexicographic permutations of 0, 1 and 2 are: 012   021   102   120   201   210
  What is the millionth lexicographic permutation of the digits 0, 1, 2, 3, 4, 5, 6, 7, 8 and 9?
 */
import EulerUtils.factorial
import scala.annotation.tailrec


object PE_024 extends App {
  val t0 = System.nanoTime()
  val target = 1000 * 1000

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


  println("Result: " + check(target, 9, List(), List.range(0, 10, 1))) // List(2, 7, 8, 3, 9, 1, 5, 4, 6, 0)
  println("Elapsed time: " + (System.nanoTime() - t0) / 1e9 + "s")
}
