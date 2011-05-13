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
class PuzzleSpec extends SpecificationWithJUnit {

  var puzzle : Puzzle = new Puzzle

  "A puzzle" should {

    doBefore {
      puzzle = (
          (1, 2, 3, 4, 5, 6, 7, 8, 9),
          (2, 3, 4, 5, 6, 7, 8, 9, 1),
          (3, 4, 5, 6, 7, 8, 9, 1, 2),
          (4, 5, 6, 7, 8, 9, 1, 2, 3),
          (5, 6, 7, 8, 9, 1, 2, 3, 4),
          (6, 7, 8, 9, 1, 2, 3, 4, 5),
          (7, 8, 9, 1, 2, 3, 4, 5, 6),
          (8, 9, 1, 2, 3, 4, 5, 6, 7),
          (9, 1, 2, 3, 4, 5, 6, 7 ,8)
      )
    }

    "be properly initialized on creation" in {
      val newPuzzle = new Puzzle
      for (rcnt <- 0 until Puzzle.NumberRows) {
        for (ccnt <- 0 until Puzzle.RowLength) {
          newPuzzle.cell(rcnt)(ccnt) must_== 0
        }
      }
    }

    "return missing values from a row" in {
      puzzle.setCell(0)(0)(Puzzle.EmptyValue)
      puzzle.setCell(0)(4)(Puzzle.EmptyValue)
      val missingVals = puzzle.missingValuesInRow(0)
      missingVals must_== List(1, 5)
    }

    "return all the missing values in a puzzle" in {
      puzzle.setCell(0)(0)(Puzzle.EmptyValue)
      puzzle.setCell(0)(4)(Puzzle.EmptyValue)

      puzzle.setCell(1)(2)(Puzzle.EmptyValue)

      puzzle.setCell(5)(5)(Puzzle.EmptyValue)
      puzzle.setCell(5)(8)(Puzzle.EmptyValue)

      val missingVals = puzzle.missingValues
      missingVals(0) must_== List(1, 5)
      missingVals(1) must_== List(4)
      missingVals(2) must_== List()
      missingVals(3) must_== List()
      missingVals(4) must_== List()
      missingVals(5) must_== List(2, 5)
      missingVals(6) must_== List()
      missingVals(7) must_== List()
      missingVals(8) must_== List()
    }

    "return correct cell values" in {
      // note that this also tests the implicit conversion from tuple to puzzle.
      for (rcnt <- 0 until 9) {
        for (ccnt <- 0 until 9) {
          puzzle.cell(rcnt)(ccnt) must_== (rcnt + ccnt) % 9 + 1
        }
      }
    }

    "throw exceptions for bad cell indices" in {
      puzzle.cell(-1)(2) must throwAn[IllegalArgumentException]
      puzzle.cell(2)(-2) must throwAn[IllegalArgumentException]
      puzzle.cell(11)(4) must throwAn[IllegalArgumentException]
      puzzle.cell(4)(11) must throwAn[IllegalArgumentException]
    }

    "properly set cell values" in {
      puzzle.cell(1)(1) must_== 3
      puzzle.setCell(1)(1)(9)
      puzzle.cell(1)(1) must_== 9
    }

    "throw exceptions for bad indices when setting cell values" in {
      puzzle.setCell(-1)(1)(1) must throwAn[IllegalArgumentException]
      puzzle.setCell(1)(-1)(1) must throwAn[IllegalArgumentException]
      puzzle.setCell(10)(1)(1) must throwAn[IllegalArgumentException]
      puzzle.setCell(1)(10)(1) must throwAn[IllegalArgumentException]
    }

    "properly set rows from a list of values" in {
      puzzle.setRow(2)(9, 8, 7, 6, 5, 4, 3, 2, 1)
      puzzle.cell(2)(0) must_== 9
      puzzle.cell(2)(1) must_== 8
      puzzle.cell(2)(2) must_== 7
      puzzle.cell(2)(3) must_== 6
      puzzle.cell(2)(4) must_== 5
      puzzle.cell(2)(5) must_== 4
      puzzle.cell(2)(6) must_== 3
      puzzle.cell(2)(7) must_== 2
      puzzle.cell(2)(8) must_== 1
    }

    "properly set rows from an array" in {
      val row = Array[Int](9, 8, 7, 6, 5, 4, 3, 2, 1)
      puzzle.setRowFromArray(2)(row)
      puzzle.cell(2)(0) must_== 9
      puzzle.cell(2)(1) must_== 8
      puzzle.cell(2)(2) must_== 7
      puzzle.cell(2)(3) must_== 6
      puzzle.cell(2)(4) must_== 5
      puzzle.cell(2)(5) must_== 4
      puzzle.cell(2)(6) must_== 3
      puzzle.cell(2)(7) must_== 2
      puzzle.cell(2)(8) must_== 1
    }

    "properly set rows from a list" in {
      val row = List[Int](9, 8, 7, 6, 5, 4, 3, 2, 1)
      puzzle.setRowFromList(2)(row)
      puzzle.cell(2)(0) must_== 9
      puzzle.cell(2)(1) must_== 8
      puzzle.cell(2)(2) must_== 7
      puzzle.cell(2)(3) must_== 6
      puzzle.cell(2)(4) must_== 5
      puzzle.cell(2)(5) must_== 4
      puzzle.cell(2)(6) must_== 3
      puzzle.cell(2)(7) must_== 2
      puzzle.cell(2)(8) must_== 1
    }

    "throw exceptions for bad indices when setting row values" in {
      puzzle.setRow(-1)(1, 2, 3, 4, 5, 6, 7, 8, 9) must throwAn[IllegalArgumentException]
      puzzle.setRow(10)(1, 2, 3, 4, 5, 6, 7, 8, 9) must throwAn[IllegalArgumentException]
    }

    // isempty
    "properly indicate when cells are empty" in {
      // iniitialy no empty cells.
      for (rcnt <- 0 until 9) {
        for (ccnt <- 0 until 9) {
          puzzle.isEmpty(rcnt)(ccnt) must beFalse
        }
      }

      puzzle.setCell(1)(1)(-1)
      puzzle.isEmpty(1)(1) must beTrue
      puzzle.setCell(2)(2)(11)
      puzzle.isEmpty(2)(2) must beTrue
    }

    "throw exceptions for bad indices when checking for empty cells" in {
      puzzle.isEmpty(-1)(1) must throwAn[IllegalArgumentException]
      puzzle.isEmpty(1)(-1) must throwAn[IllegalArgumentException]
      puzzle.isEmpty(11)(1) must throwAn[IllegalArgumentException]
      puzzle.isEmpty(1)(11) must throwAn[IllegalArgumentException]
    }

    "properly return rows" in {
      val row = puzzle.row(3)

      row(0) must_== 4
      row(1) must_== 5
      row(2) must_== 6
      row(3) must_== 7
      row(4) must_== 8
      row(5) must_== 9
      row(6) must_== 1
      row(7) must_== 2
      row(8) must_== 3
    }

    "throw exceptions for bad indices when accessing rows" in {
      puzzle.row(-1) must throwAn[IllegalArgumentException]
      puzzle.row(9) must throwAn[IllegalArgumentException]
    }

    "propoerly return columns" in {
      val col = puzzle.column(3)

      col(0) must_== 4
      col(1) must_== 5
      col(2) must_== 6
      col(3) must_== 7
      col(4) must_== 8
      col(5) must_== 9
      col(6) must_== 1
      col(7) must_== 2
      col(8) must_== 3
    }

    "throw exceptions for bad indices when accessing columns" in {
      puzzle.column(-1) must throwAn[IllegalArgumentException]
      puzzle.column(9) must throwAn[IllegalArgumentException]
    }

    "propoerly return mini-grids" in {
      val mg = puzzle.miniGrid(3)

      // second row of mini-grids, first column.
      mg(0) must_== 4
      mg(1) must_== 5
      mg(2) must_== 6
      mg(3) must_== 5
      mg(4) must_== 6
      mg(5) must_== 7
      mg(6) must_== 6
      mg(7) must_== 7
      mg(8) must_== 8
    }

    "throw exceptions for bad indices when accessing mini-grids" in {
      puzzle.miniGrid(-1) must throwAn[IllegalArgumentException]
      puzzle.miniGrid(9) must throwAn[IllegalArgumentException]
    }

    "convert from strings to puzzles" in {
      val stringPuzzle =
      """1, 2, 3, 4, 5, 6, 7, 8, 9
         0, 2, 3, 4, 5, 6, 7, 8, 9
         1,  , 3, 4, 5, 6, 7, 8, 9
         1,22, 3, 4, 5, 6, 7, 8,
         1, 2, 3, 4, 5, 6, 7, 8, 0
         1, 2, 3, 4, 5, 6, a, 8, 9
         1, 2, 3, 4, 5, 6, 7, 8, 9
         1, 2, 3, 4, 5, 6, 7, 8, 9
         1, 2, 3, 4, 5, 6, 7, 8"""

      val puzzle : Puzzle = stringPuzzle
      puzzle.cell(0)(0) must_== 1
      puzzle.cell(5)(5) must_== 6
      Puzzle.emptyVal(puzzle.cell(1)(0)) mustBe true
      Puzzle.emptyVal(puzzle.cell(2)(1)) mustBe true
      Puzzle.emptyVal(puzzle.cell(3)(1)) mustBe true
      Puzzle.emptyVal(puzzle.cell(3)(8)) mustBe true
      Puzzle.emptyVal(puzzle.cell(4)(8)) mustBe true
      Puzzle.emptyVal(puzzle.cell(8)(8)) mustBe true
    }

  }
}

