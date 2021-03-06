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

import java.util.Date
import scala.io.Source

/**
 * The SudokuSolver is a tool that solves sudoku puzzles using a possible variety of approaches.
 * There are three ways to enter the puzzle to solve:
 * 1.  As a single line with nine sets of nine entries or spaces separated by a comma.
 * 2.  As a 9x9 grid in a file.
 * 3.  From the command line prompt, one line at a time of nine entries.
 * @author Bill Back
 */
object SudokuSolver extends App {

  // get the data from the args.
  var puzzleInput = new StringBuilder
  if (args.length > 0) { // should be name of a file.
    val src = Source.fromFile(args(0))
    puzzleInput.append (src.mkString)
  }
  else { // prompt for the lines on the command line.
    println ("Enter each line of the puzzle with ',' separating the values.  Empty values can be blank spaces or zeros.")
    (1 to 0) foreach { cnt =>
      println ("Line " + cnt + ":  ")
      val src = Source.stdin.getLines
      puzzleInput.append (src.mkString)
    }
  }
  println ("Puzzle to solve:\n" + puzzleInput.toString)

  val puzzle = puzzleInput.toString
  // track start time.
  val start = new Date().getTime
  val solver = new BruteForceSolver
  //val solver = new RandomSolver
  solver.showStatus = true

  println( "Solving puzzle using " + solver.name + " solver.")
  val solution = solver.solve(puzzle)

  val itWasSolved = Verify(solution)

  // show time to complete.
  println ((if (itWasSolved) "solved" else "not solved") + " in " + (new Date().getTime - start) + " milliseconds")

  println ("Solution:\n")
  println(solution.toString)

}