package EulerSolutions

/*
Using names.txt (right click and 'Save Link/Target As...'), a 46K text file containing over five-thousand first names,
 begin by sorting it into alphabetical order. Then working out the alphabetical value for each name, multiply this
 value by its alphabetical position in the list to obtain a name score.

For example, when the list is sorted into alphabetical order, COLIN, which is worth 3 + 15 + 12 + 9 + 14 = 53,
is the 938th name in the list. So, COLIN would obtain a score of 938 × 53 = 49714.

What is the total of all the name scores in the file?
 */


object PE_022 extends App {

  val alphabet = List('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
    'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z')
  val alphabetMapping = (1 to alphabet.length).map(i => (alphabet(i-1), i)).toMap
  val names = scala.io.Source.fromFile("Resources/p022_names.txt").mkString.
    replaceAll("\"", "").toLowerCase.split(",").sorted

  val t0 = System.nanoTime()
  val result = names.indices.foldLeft(0)((s, i) => s + (i+1) * names(i).foldLeft(0)((s2, c) => s2 + alphabetMapping(c)))
  println("Result: " + result) // 871198282
  println("Elapsed time: " + (System.nanoTime() - t0) / 1e9 + "s")
}
