package com.thebackfamily.sudoku

/**
 * Trait implemented by all solvers.
 * @author Bill Back
 */
trait Solver {

  /** Name of the solver to show to users. */
  val name : String

  /**
   * Solves the puzzle.
   * @param puzzle 9x9 array of integers.  For each entry a negative number indicates a blank space.
   * @return The results as a 9x9 array of integers.
   */
  def solve (puzzle : Array[Array[Int]]) : Array[Array[Int]]
}