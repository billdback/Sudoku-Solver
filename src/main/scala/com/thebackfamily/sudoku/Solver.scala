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
   * @param puzzle For each entry a negative number indicates a blank space.
   * @return The results.
   */
  def solve (puzzle : Puzzle) : Puzzle
}