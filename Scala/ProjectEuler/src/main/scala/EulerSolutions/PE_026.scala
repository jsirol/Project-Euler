package EulerSolutions

/*
A unit fraction contains 1 in the numerator. The decimal representation of the unit fractions with denominators 2 to 10 are given:
1/2	= 	0.5
1/3	= 	0.(3)
1/4	= 	0.25
1/5	= 	0.2
1/6	= 	0.1(6)
1/7	= 	0.(142857)
1/8	= 	0.125
1/9	= 	0.(1)
1/10	= 	0.1
Where 0.1(6) means 0.166666..., and has a 1-digit recurring cycle. It can be seen that 1/7 has a 6-digit recurring cycle.
Find the value of d < 1000 for which 1/d contains the longest recurring cycle in its decimal fraction part.
*/

object PE_026 extends App {

  def computeFractionRecurringCycle(n: Int): Int = {
    def cycleIter(currentNumerator: Int, numeratorsUsed: List[Int]): Int = {
      val nextNumerator = (currentNumerator % n) * 10
      if (nextNumerator == 0) 0 // no cycle
      else if (numeratorsUsed.contains(nextNumerator)) numeratorsUsed.size - numeratorsUsed.takeWhile(x => x == nextNumerator).size
      else cycleIter(nextNumerator, nextNumerator :: numeratorsUsed)
    }
    cycleIter(1, List())
  }

  val t0 = System.nanoTime()
  val result = (1 until 1000).map(x => (computeFractionRecurringCycle(x), x)).reduce((x, y) => if (y._1 > x._1) y else x)._2
  println("Result: " + result) // 983
  println("Elapsed time: " + (System.nanoTime() - t0) / 1e9 + "s")
}