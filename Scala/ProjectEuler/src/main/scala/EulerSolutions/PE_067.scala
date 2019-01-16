package EulerSolutions

/* By starting at the top of the triangle below and moving to adjacent numbers on the row below, the maximum total from top to bottom is 23.

3
7 4
2 4 6
8 5 9 3

That is, 3 + 7 + 4 + 9 = 23.

Find the maximum total from top to bottom in triangle.txt (right click and 'Save Link/Target As...'), a 15K
text file containing a triangle with one-hundred rows.

NOTE: This is a much more difficult version of Problem 18. It is not possible to try every route to solve
this problem, as there are 299 altogether! If you could check one trillion (1012) routes every second it would take over
twenty billion years to check them all. There is an efficient algorithm to solve it. ;o)
*/

object PE_067 extends App {

  val triangle = scala.io.Source.fromFile("Resources/p067_triangle.txt").getLines().mkString("\n")

  val splitted = triangle.
    split("\n").
    map(s => s.stripMargin.split("\\s+").map(x => x.stripPrefix("0").toLong))

  def maxPath(previousRow: Array[Long], currentRow: Array[Long], index: Int): Long = {
    index match {
      case 0 => currentRow(index) + previousRow(index)
      case _ if index == previousRow.length => currentRow(index) + previousRow(index-1)
      case _ => currentRow(index) + math.max(previousRow(index), previousRow(index-1))
    }
  }

  val t0 = System.nanoTime()
  val result = splitted.
    tail.
    foldLeft(splitted(0))((previousRow, currentRow) => currentRow.indices.map(i => maxPath(previousRow, currentRow, i)).toArray).
    max
  println("Result: " + result) // 7273
  println("Elapsed time: " + (System.nanoTime() - t0) / 1e9 + "s")
}
