package EulerSolutions

import EulerSolutions.EulerUtils.binomial


object PE_015 extends App {
/*  // Using dynamic programming
  type Grid = Array[Array[BigInt]]
  val size = 20
  val numLattices = size + 1
  var grid = Array.tabulate[BigInt](numLattices, numLattices) { (row,col) => if (row == 0 || col == 0) 1 else 0}

  def evolveGrid(grid: Grid, row: Int, col: Int): Unit = {
    grid(row)(col) = grid(row-1)(col) + grid(row)(col-1)
  }

  val t0 = System.nanoTime()
  (1 until numLattices).
    foreach(row => (1 until numLattices).
      foreach(col => evolveGrid(grid, row, col)))

  println("Result: " + grid(numLattices-1)(numLattices-1)) // 137846528820
  println("Elapsed time: " + (System.nanoTime() - t0) / 1e9 + "s")*/

  // using direct solution
  /*
  Equivalent of (2*size)C(size)
  Explanation:
  You need to take 2*size steps to get to the goal, out of which exactly *size* amount needs to be down and right,
  thus the question becomes "in how many ways can you pick the right (or down) moves from 40 available actions out
  of which 20 are down and 20 right?"
   */

  val t0 = System.nanoTime()
  val size = 20
  println("Result: " + binomial(size*2, size)) // 137846528820
  println("Elapsed time: " + (System.nanoTime() - t0) / 1e9 + "s")
}
