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
 * Represents a sudoku puzzle, which is a 9x9 grid.
 * @author Bill Back
 */
class Puzzle {

  // Internal puzzle to use for storage.
  val puzzle : Array[Array[Int]] = Array.ofDim(9, 9)

  /**
   * Returns the value at the row and column.  Rows and colums are numbered from 0-8.
   * @param rcnt The row count of the item to return. 0 to 8.
   * @param ccnt The column count of the item to return. 0 to 8.
   */
  def cell(rcnt : Int)(ccnt : Int) : Int = {
    require(rcnt >= 0 && rcnt < Puzzle.NumberRows, "row index not in range 0-8")
    require(ccnt >= 0 && ccnt < Puzzle.RowLength,  "column index not in range 0-8")

    puzzle(rcnt)(ccnt)
  }

  /**
   * Sets the value of a particular spot in the puzzle.
   * @param rcnt The row count of the item to return. 0 to 8.
   * @param ccnt The column count of the item to return. 0 to 8.
   */
  def setCell(rcnt : Int)(ccnt : Int)(value : Int) = {
    require(rcnt >= 0 && rcnt < Puzzle.NumberRows, "row index not in range 0-8")
    require(ccnt >= 0 && ccnt < Puzzle.RowLength,  "column index not in range 0-8")

    puzzle(rcnt)(ccnt) = value
  }

  /**
   * Returns the value at the row and column.  Rows and colums are numbered from 0-8.
   * @param rcnt The row count of the item to return. 0 to 8.
   * @param values The values to populate the row with.
   */
  def setRow(rcnt : Int)(values : (Int, Int, Int, Int, Int, Int, Int, Int, Int)) = {
    require(rcnt >= 0 && rcnt < Puzzle.NumberRows, "row index not in range 0-8")

    setCell(rcnt)(0)(values._1)
    setCell(rcnt)(1)(values._2)
    setCell(rcnt)(2)(values._3)
    setCell(rcnt)(3)(values._4)
    setCell(rcnt)(4)(values._5)
    setCell(rcnt)(5)(values._6)
    setCell(rcnt)(6)(values._7)
    setCell(rcnt)(7)(values._8)
    setCell(rcnt)(8)(values._9)
  }

  /**
   * Sets the value of a row from a list.
   * @param rcnt The row to set.
   * @param list The list to populate the row from.  Must contain a row's worth of values.
   */
  def setRowFromList (rcnt : Int)(list : List[Int]) = {
    require(rcnt >= 0 && rcnt < Puzzle.NumberRows, "row index not in range 0-8")
    require (list.length == Puzzle.RowLength, "list must be " + Puzzle.NumberRows + " items.")

    setRowFromArray(rcnt)(list.toArray)
  }

  /**
   * Sets the value of a row from a list.
   * @param rcnt The row to set.
   * @param list The list to populate the row from.  Must contain a row's worth of values.
   */
  def setRowFromArray (rcnt : Int)(arr : Array[Int]) = {
    require(rcnt >= 0 && rcnt < Puzzle.NumberRows, "row index not in range 0-8")
    require (arr.length == Puzzle.RowLength, "array must be " + Puzzle.NumberRows + " items.")

    for (ccnt <- 0 until Puzzle.RowLength) {
      setCell(rcnt)(ccnt)(arr(ccnt))
    }
  }

  /**
   * Returns true if the given cell is empty.
   * @param rcnt The row count of the item to check. 0 to 8.
   * @param ccnt The column count of the item to check. 0 to 8.
   * @return true if the given cell is empty.
   */
  def isEmpty(rcnt : Int)(ccnt : Int) : Boolean = {
    require(rcnt >= 0 && rcnt < Puzzle.NumberRows, "row index not in range 0-8")
    require(ccnt >= 0 && ccnt < Puzzle.RowLength,  "column index not in range 0-8")

    Puzzle.emptyVal(puzzle(rcnt)(ccnt))
  }

  /**
   * Returns the given row (0-8).
   * @return The given row (0-8).
   */
  def row(rcnt : Int) : Array[Int] = {
    require(rcnt >= 0 && rcnt < Puzzle.NumberRows, "row index not in range 0-8")
    puzzle(rcnt)
  }

  /**
   * Returns the given column (0-8).
   * @return The given column (0-8).
   */
  def column(ccnt : Int) : Array[Int] = {
    require(ccnt >= 0 && ccnt < Puzzle.RowLength, "column index not in range 0-8")
    val col : Array[Int] = Array.ofDim(Puzzle.RowLength)
    for (cnt <- 0 until Puzzle.RowLength) {
      col(cnt) = puzzle(cnt)(ccnt)
    }
    col
  }

  /**
   * Returns the mini-grid.  There are nine 3x3 mini-grids starting from the upper
   * left corner.
   * @return The mini-grid.
   */
  def miniGrid(mgcnt : Int) : Array[Int] = {
    require(mgcnt >= 0 && mgcnt < Puzzle.RowLength, "mini-grid index not in range 0-8")

    var rcnt : Int = 0
    var ccnt : Int = 0

    rcnt = mgcnt match {
      case 0 => 0
      case 1 => 0
      case 2 => 0
      case 3 => 3
      case 4 => 3
      case 5 => 3
      case 6 => 6
      case 7 => 6
      case 8 => 6
    }
    ccnt = (mgcnt % 3) * 3

    // rcnt and ccnt are pointing to the upper left entry of the mini grid.
    val res : Array[Int] = Array.ofDim(Puzzle.RowLength)
    res(0) = puzzle(rcnt)(ccnt)
    res(1) = puzzle(rcnt)(ccnt + 1)
    res(2) = puzzle(rcnt)(ccnt + 2)
    res(3) = puzzle(rcnt + 1)(ccnt)
    res(4) = puzzle(rcnt + 1)(ccnt + 1)
    res(5) = puzzle(rcnt + 1)(ccnt + 2)
    res(6) = puzzle(rcnt + 2)(ccnt)
    res(7) = puzzle(rcnt + 2)(ccnt + 1)
    res(8) = puzzle(rcnt + 2)(ccnt + 2)

    res
  }

  /**
   * Returns the missing values for a given row.
   * @param rcnt The row to get the missing values for.
   * @param
   */
  def missingValuesInRow (rcnt : Int) : List[Int] = {
    val hasVals = row(rcnt).toList.filterNot(aval => Puzzle.emptyVal(aval))
    Puzzle.ValidValues -- hasVals
  }

  /**
   * Returns a list of all missing values in the puzzle by row.
   * @return A list of all missing values in the puzzle by row.
   */
  def missingValues : Array[List[Int]] = {
    val missingVals : Array[List[Int]] = Array.ofDim(Puzzle.NumberRows)
    for (rcnt <- 0 until Puzzle.NumberRows) {
      missingVals(rcnt) = missingValuesInRow(rcnt)
    }
    missingVals
  }

  /**
   * Formats the puzzle as a friendly string for printing.
   * @return The puzzle as a friendly string for printing.
   */
  override def toString () : String = {
    var res : String = ""
    puzzle.foreach { row =>
      res += row.mkString(", ") + "\n"
    }
    res
  }

  /**
   * Returns true if the other puzzle has the same values as this one.
   * @param other The other puzzle to compare for equality.
   * @return True if the other puzzle has the same values as this one.
   */
  override def equals (other : Any) = {
    var areEqual = other.isInstanceOf[Puzzle]
    for (rcnt <- 0 until 9; if areEqual) {
      areEqual = areEqual && puzzle(rcnt) == other.asInstanceOf[Puzzle].row(rcnt)
    }
    areEqual
  }
}

/**
 * Companion object.
 * @author Bill Back
 */
object Puzzle {

  val RowLength   = 9  // Number of items in a row.
  val NumberRows  = 9  // Number of rows in a puzzle.
  val EmptyValue  = 0  // A value that represents an empty puzzle location.
  val ValidValues = List(1, 2, 3, 4, 5, 6, 7, 8, 9)

  /**
   * Returns true if the value is considered an empty val.
   */
  def emptyVal(aval : Int) : Boolean = {
    aval < 1 || aval > 9
  }

  /**
   * Converts from a tuple to a puzzle.  Provides a convenience method to make assignment
   * pretty.
   * @param vals The values to assign to the puzzle.
   * @return A puzzle with the given values.
   */
  implicit def tupleToPuzzle(vals : ((Int, Int, Int, Int, Int, Int, Int, Int, Int),
                                     (Int, Int, Int, Int, Int, Int, Int, Int, Int),
                                     (Int, Int, Int, Int, Int, Int, Int, Int, Int),
                                     (Int, Int, Int, Int, Int, Int, Int, Int, Int),
                                     (Int, Int, Int, Int, Int, Int, Int, Int, Int),
                                     (Int, Int, Int, Int, Int, Int, Int, Int, Int),
                                     (Int, Int, Int, Int, Int, Int, Int, Int, Int),
                                     (Int, Int, Int, Int, Int, Int, Int, Int, Int),
                                     (Int, Int, Int, Int, Int, Int, Int, Int, Int)
                                    ))
    : Puzzle = {

    val puzzle = new Puzzle
    puzzle.setRow(0)(vals._1)
    puzzle.setRow(1)(vals._2)
    puzzle.setRow(2)(vals._3)
    puzzle.setRow(3)(vals._4)
    puzzle.setRow(4)(vals._5)
    puzzle.setRow(5)(vals._6)
    puzzle.setRow(6)(vals._7)
    puzzle.setRow(7)(vals._8)
    puzzle.setRow(8)(vals._9)

    puzzle

  }
}