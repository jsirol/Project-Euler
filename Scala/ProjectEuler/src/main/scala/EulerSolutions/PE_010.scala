package EulerSolutions
/*
The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.

Find the sum of all the primes below two million.
 */
import EulerUtils.isPrime

object PE_010 extends App {

  val t0 = System.nanoTime()
  val twoMillion = 2000 * 1000L
  val result = 2 + (3L until(twoMillion, 2L)).par.filter(x => isPrime(x)).sum
  println("Result: " + result) // 142913828922
  println("Elapsed time: " + (System.nanoTime() - t0) / 1e9 + "s")
}
