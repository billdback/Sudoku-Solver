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
 * Uses a brute force method to solve the puzzle.
 * @author Bill Back
 */
class BruteForceSolver extends Solver {
  /** Name of this solver. */
  val name = "Brute Force"

  /**
   * Solves the puzzle using a brute force approach.
   * The brute force method simply tries all possible combinations until a correct one is reached.  It does not
   * consider the existing numbers except to not replace them.
   * @param puzzle Sudoku puzzle with some numbers filled in.
   * @return The results puzzle with all spots filled in.
   */
  def solve (puzzle : Puzzle) : Puzzle = {
    val solution = new Puzzle

    solution
  }

}