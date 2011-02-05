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
 * Verifies the puzzle to tell if it's accurate or not.
 * @author Bill Back
 */
object Verify {

  // TODO look at how to reuse the basic looping algorithm to clean this up.
  // TODO look at converting to actors to check the rows and grids independently.

  /**
   * Returns true if the puzzle is a valid solution, false otherwise.
   * @return True if the puzzle is a valid solution, false otherwise.
   */
  def apply (puzzle : Puzzle) : Boolean = {
    checkRows(puzzle) && checkColumns(puzzle) && checkMiniGrids (puzzle)
  }

  /**
   * Returns true if all rows meet the criteria for a valid puzzle solution.
   * @param puzzle The puzzle to check the rows for.
   * @return True if all of the rows contain a valid set of values.
   */
  private def checkRows (puzzle : Puzzle) : Boolean = {
    var valid = true
    for (cnt <- 0 until 9) {
      valid = valid && uniqueSet (puzzle.row(cnt))
    }
    valid
  }

  /**
   * Returns true if all columns meet the criteria for a valid puzzle solution.  This makes no assumption
   * about the number of colums other than that they are all the same size.  Standard sudoku puzzles will
   * have nine columns.
   * @param puzzle The puzzle to check the columns for.
   * @return True if all of the columns contain a valid set of values.
   */
  private def checkColumns (puzzle : Puzzle) : Boolean = {
    var valid = true
    for (cnt <- 0 until 9) {
      valid = valid && uniqueSet (puzzle.column(cnt))
    }
    valid
  }

  /**
   * Returns true if all mini-grids meet the criteria for a valid puzzle solution. A mini-grid is a three
   * by three sub-grid.  This method assumes that the solution is 9x9.
   * @param puzzle The puzzle to check the mini-grids for.
   * @return True if all of the mini-grids contain a valid set of values.
   */
  private def checkMiniGrids (puzzle : Puzzle) : Boolean = {
    var valid = true
    for (cnt <- 0 until 9) {
      valid = valid && uniqueSet (puzzle.miniGrid(cnt))
    }
    valid
  }

  /**
   * Returns true if the row has all unique values.  Note that this does not actually check
   * for the specific values, i.e. 1 to 9, so any set of unique values would return true.
   * @param set An array of numbers to check for uniqueness.
   */
  def uniqueSet (set : Array[Int]) : Boolean = {
    var valid = true
    (0 until set.length - 1) foreach { cnt =>
      (cnt + 1 until set.length) foreach { cnt2 =>
        valid = valid && (set(cnt) != set(cnt2))
      }
    }
    valid
  }
}