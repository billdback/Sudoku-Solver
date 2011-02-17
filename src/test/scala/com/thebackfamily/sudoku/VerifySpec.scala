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
 * Tests the verify object.
 */
class VerifySpec extends SpecificationWithJUnit {

  var puzzle = new Puzzle

  "A puzzle" should {
    doBefore {
      puzzle = (
          (1, 2, 3, 4, 5, 6, 7, 8, 9),
          (4, 5, 6, 7, 8, 9, 1, 2, 3),
          (7, 8, 9, 1, 2, 3, 4, 5, 6),
          (2, 3, 4, 5, 6, 7, 8, 9, 1),
          (5, 6, 7, 8, 9, 1, 2, 3, 4),
          (8, 9, 1, 2, 3, 4, 5, 6, 7),
          (3, 4, 5, 6, 7, 8, 9, 1, 2),
          (6, 7, 8, 9, 1, 2, 3, 4, 5),
          (9, 1, 2, 3, 4, 5, 6, 7 ,8)
      )
    }

    "verify if the rows, columns, and mini-grids have only unique values" in {
      Verify(puzzle) must beTrue
    }

    "not verify if there are duplicates in a single row" in {
      puzzle.setRow(1)(1,1,1,1,1,1,1,1,1)
      Verify(puzzle) must beFalse
    }

    "not verify if there are duplicates in a column" in {
      puzzle = (
        (3, 2, 1, 4, 5, 6, 7, 8, 9),
        (5, 4, 6, 7, 8, 9, 1, 2, 3),
        (7, 8, 9, 1, 2, 3, 4, 5, 6),
        (3, 2, 4, 5, 6, 7, 8, 9, 1),
        (5, 6, 7, 8, 9, 1, 2, 4, 3),
        (8, 9, 1, 2, 3, 4, 5, 7, 6),
        (3, 4, 5, 6, 7, 8, 1, 9, 2),
        (6, 7, 8, 9, 2, 1, 3, 4, 5),
        (9, 1, 2, 3, 4, 5, 6, 7, 8)
      )
      Verify(puzzle) must beFalse
    }


    "not verify if there are duplicates in a mini-grid" in {
      // Note that rows and columns are still unique.
      puzzle = (
          (4, 5, 6, 7, 8, 9, 1, 2, 3),
          (7, 8, 9, 1, 2, 3, 4, 5, 6),
          (5, 6, 7, 8, 9, 1, 2, 3, 4),
          (1, 2, 3, 4, 5, 6, 7, 8, 9),
          (2, 3, 4, 5, 6, 7, 8, 9, 1),
          (8, 9, 1, 2, 3, 4, 5, 6, 7),
          (3, 4, 5, 6, 7, 8, 9, 1, 2),
          (6, 7, 8, 9, 1, 2, 3, 4, 5),
          (9, 1, 2, 3, 4, 5, 6, 7 ,8)
      )
      Verify(puzzle) must beFalse
    }

  }
}

