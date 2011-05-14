/*
 Copyright © 2011 William D. Back
 This file is part of SudokuSolver.

    SudokuSolver is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    SudokuSolver is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Scarab.  If not, see <http://www.gnu.org/licenses/>.
*/
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