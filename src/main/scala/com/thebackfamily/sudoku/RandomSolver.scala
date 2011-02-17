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
 * Uses random values to attempt to solve the puzzle.
 * @author Bill Back
 */
class RandomSolver extends Solver {
  /** Name of this solver. */
  val name = "Ramdon"

  /**
   * Solves the puzzle using random numbers.
   * The random approach simply puts a random number from 1-9 in each empty cell
   * and sees if that solves the problem.  This limits tries to 1,000,000 to prevent
   * never returning.
   * @param puzzle Sudoku puzzle with some numbers filled in.
   * @return The results puzzle with all spots filled in.
   */
  def solve (puzzle : Puzzle) : Puzzle = {
    var solution : Puzzle = new Puzzle
    var solved = false
    val rand = new scala.util.Random
    var tries = 0

    while (!solved && tries < 1000000) {
      tries += 1
      for (rcnt <- (0 until 9)) {
        for (ccnt <- (0 until 9)) {
          if (puzzle.isEmpty(rcnt)(ccnt)) {
            solution.setCell(rcnt)(ccnt)(rand.nextInt(9) + 1)
          }
          else {
            solution.setCell(rcnt)(ccnt)(puzzle.cell(rcnt)(ccnt))
          }
        }
      }
      solved = Verify(solution)
    }

    if (tries >= 1000000) println ("Giving up random solution.")

    solution
  }

}