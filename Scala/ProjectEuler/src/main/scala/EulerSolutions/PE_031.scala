package EulerSolutions

/*
In England the currency is made up of pound, £, and pence, p, and there are eight coins in general circulation:
1p, 2p, 5p, 10p, 20p, 50p, £1 (100p) and £2 (200p).
It is possible to make £2 in the following way:
1×£1 + 1×50p + 2×20p + 1×5p + 1×2p + 3×1p
How many different ways can £2 be made using any number of coins?
*/

object PE_031 extends App {

  def coinCombinations(money: Int, coins: List[Int]): Int = {
    def coinCombinationsIter(currentMoney: Int, coinsToUse: List[Int]): Int = {
      if (currentMoney <= 0 || coinsToUse.isEmpty) 0
      else if (currentMoney - coinsToUse.head == 0) 1
      else coinCombinationsIter(currentMoney - coinsToUse.head, coinsToUse) + coinCombinationsIter(currentMoney, coinsToUse.tail)
    }
    coinCombinationsIter(money, coins.sorted)
  }

  val t0 = System.nanoTime()
  val coins = List(1, 2, 5, 10, 20, 50, 100, 200)
  val result = coinCombinations(200, coins)
  println("Result: " + result) // 73682
  println("Elapsed time: " + (System.nanoTime() - t0) / 1e9 + "s")
}
