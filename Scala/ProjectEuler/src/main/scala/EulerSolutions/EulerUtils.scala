package EulerSolutions

import scala.annotation.tailrec

object EulerUtils {

  def factorial(n: Int): BigInt = {
    @tailrec
    def factorial_iter(acc: BigInt, n: Int): BigInt = {
      if (n == 1) acc else  factorial_iter(n * acc, n - 1)
    }
    if (n < 0) throw new IllegalArgumentException
    else if (n == 0) 1
    else factorial_iter(1, n)
  }

  def binomial(n: Int, k: Int): BigInt = {
    if (k < 0 | k > n) throw new IllegalArgumentException
    (BigInt(k+1) to BigInt(n)).product / factorial(k)
  }
}


