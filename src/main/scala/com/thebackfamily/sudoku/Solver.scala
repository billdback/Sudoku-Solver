package com.thebackfamily.sudoku

/**
 * Trait implemented by all solvers.
 * @author Bill Back
 */
trait Solver {

  /** Name of the solver to show to users. */
  val name : String

  /**
   * Optional ability to show status.  If a solver does support showing status as it solves the puzzle, this
   * flag will determine if that is on or off.  By default it is off.
   */
  var showStatus = false

  /**
   * Solves the puzzle.
   * @param puzzle For each entry a negative number indicates a blank space.
   * @return The results.
   */
  def solve (puzzle : Puzzle) : Puzzle
}