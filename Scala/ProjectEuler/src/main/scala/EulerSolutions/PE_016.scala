package EulerSolutions

/*
215 = 32768 and the sum of its digits is 3 + 2 + 7 + 6 + 8 = 26.

What is the sum of the digits of the number 2^1000?
 */

object PE_016 extends App {

  val t0 = System.nanoTime()
  val hugeNumber = BigInt(2).pow(1000)
  val result = hugeNumber.toString().split("").foldLeft(0)((s, x) => s + x.toInt)
  println("Result: " + result) // 1366
  println("Elapsed time: " + (System.nanoTime() - t0) / 1e9 + "s")
}
