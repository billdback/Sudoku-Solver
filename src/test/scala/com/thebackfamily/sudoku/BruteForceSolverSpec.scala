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

import org.specs._

/**
 * Tests the BruteForceSolver.
 */
class BruteForceSolverSpec extends SpecificationWithJUnit {

  "A BruteForceSolver" should {

    "find the correct solution" in {
      /** This takes too long to verify as currently coded.  Using an easier puzzle.
      val puzzle = (
          (0, 2, 0, 0, 0, 0, 0, 0, 0),
          (0, 0, 6, 7, 8, 9, 1, 2, 3),
          (7, 8, 9, 1, 2, 3, 4, 5, 6),
          (0, 0, 0, 0, 0, 0, 8, 9, 1),
          (0, 0, 7, 8, 0, 0, 2, 0, 0),
          (0, 0, 1, 2, 0, 4, 5, 0, 0),
          (0, 0, 5, 6, 7, 8, 0, 0, 2),
          (0, 0, 8, 9, 0, 0, 3, 4, 5),
          (9, 1, 2, 3, 4, 5, 6, 7 ,8)
      )
      */

      val puzzle = (
          (0, 2, 3, 4, 5, 6, 7, 8, 9),
          (4, 0, 0, 7, 8, 9, 1, 2, 3),
          (7, 8, 0, 1, 2, 3, 4, 5, 6),
          (2, 3, 4, 0, 6, 7, 8, 9, 1),
          (5, 6, 7, 8, 0, 1, 0, 3, 0),
          (8, 9, 1, 2, 3, 0, 5, 6, 7),
          (3, 4, 5, 6, 7, 8, 0, 1, 2),
          (6, 7, 8, 9, 1, 2, 3, 0, 5),
          (9, 1, 2, 3, 4, 5, 6, 7 ,0)
      )
      val bfs = new BruteForceSolver

      val solution = bfs.solve(puzzle)
      println (solution)

      Verify(solution) mustBe true
    }

  }
}

