package EulerSolutions

/*
If the numbers 1 to 5 are written out in words: one, two, three, four, five, then there are 3 + 3 + 5 + 4 + 4 = 19
letters used in total.
If all the numbers from 1 to 1000 (one thousand) inclusive were written out in words, how many letters would be used?
NOTE: Do not count spaces or hyphens. For example, 342 (three hundred and forty-two) contains 23 letters and 115
(one hundred and fifteen) contains 20 letters. The use of "and" when writing out numbers is in compliance with British usage.
 */

object PE_017 extends App {

  def numberToString(n: Int): String = {
    if (n < 0 || n > 1000) throw new IllegalArgumentException("n must be between 1 and 1000")

    val lessThanTwentyList = List("one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
      "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen")
    val tensFromTwentyList = List("twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety")

    def numberToStringIter(current: Int, currentString: String): String = {
      if (current == 0 && currentString != "") currentString
      else if (n < 20) numberToStringIter(0, currentString + lessThanTwentyList(n - 1))
      else {
        current match {
          case _ if current % 1000 == 0 =>
            numberToStringIter(current % 1000, "onethousand") // 1000's
          case _ if current > 100 && current % 100 > 0 =>
            numberToStringIter(current % 100, lessThanTwentyList((current / 100)-1) + "hundredand") // hundreds
          case _ if current % 100 == 0 =>
            numberToStringIter(current % 100, currentString + lessThanTwentyList((current / 100)-1) + "hundred") // hundreds (even)
          case _ if current >= 20 =>
            numberToStringIter(current % 10, currentString + tensFromTwentyList(current/10 - 2)) // remaining >= 20
          case _ =>
            numberToStringIter(0, currentString + lessThanTwentyList(current - 1)) // remaining <20
        }
      }
    }
    numberToStringIter(n, "")
  }

  val t0 = System.nanoTime()
  val result = (1 to 1000).foldLeft(0)((s, x) => s + numberToString(x).length)
  println("Result: " + result) // 21124
  println("Elapsed time: " + (System.nanoTime() - t0) / 1e9 + "s")
}
