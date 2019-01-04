package EulerSolutions

object PE_001 extends App {
  def sumOfMultiples(max: Int): Int = {

    def include(acc: Int, value: Int): Int = if (value % 3 == 0 || value % 5 == 0) value + acc else acc

    (1 to max).foldLeft(0)(include)
  }

  val t0 = System.nanoTime()
  println("Result: " + sumOfMultiples(1000)) // 234168
  println("Elapsed time: " + (System.nanoTime() - t0) / 1e9 + "s")

}
