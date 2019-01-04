package EulerSolutions

import scala.annotation.tailrec

object PE_002 extends App {
  val fourMillion = 4 * 1000 * 1000

  @tailrec
  def evenFiboSum(acc: Int, previous: Int, previous2: Int, maxTerm: Int): Int = {
    val current = previous + previous2
    if (current >= maxTerm)
      acc
    else if (current % 2 == 0)
      evenFiboSum(acc + current, current, previous, maxTerm)
    else
      evenFiboSum(acc, current, previous, maxTerm)
  }

  val t0 = System.nanoTime()
  println("Result: " + evenFiboSum(acc=2, previous=2, previous2=1, maxTerm=fourMillion)) // 4613732
  println("Elapsed time: " + (System.nanoTime() - t0) / 1e9 + "s")
}
