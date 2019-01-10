package EulerSolutions
/*
A Pythagorean triplet is a set of three natural numbers, a < b < c, for which,

a2 + b2 = c2
For example, 32 + 42 = 9 + 16 = 25 = 52.

There exists exactly one Pythagorean triplet for which a + b + c = 1000.
Find the product abc.
 */

object PE_009 extends App {
  def isPythagoreanTriplet(a: Int, b: Int, c: Int): Boolean = a*a + b*b == c*c
  val t0 = System.nanoTime()
  val sumVal = 1000
  val result = for {
    c <- sumVal/3 to sumVal-2
    b <- 2 until math.min(c, sumVal-c)
    a = math.min(sumVal-b-c, b-1)
    if a+b+c == 1000 && isPythagoreanTriplet(a,b,c)
  } yield a*b*c
  println("Result: " + result(0)) // 31875000
  println("Elapsed time: " + (System.nanoTime() - t0) / 1e9 + "s")
}
