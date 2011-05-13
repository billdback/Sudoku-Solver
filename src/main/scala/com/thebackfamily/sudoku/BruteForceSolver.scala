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

import scala.collection.mutable.Map
import java.util.Date

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
    // get the possible combinations for each row to make it easy to vary over them.
    val combinations = getValueCombinations(puzzle)

    findSolution (combinations)
  }

  /**
   * Returns a map with all possible, valid combiations for each row.  The index is the row and the
   * values are lists or ordered values.
   * @param puzzle The puzzle to get the valid values for.
   * @return All possible combinations of possible values.
   */
  private def getValueCombinations(puzzle : Puzzle) : Map[Int, List[List[Int]]] = {

    val missingValues : Array[List[Int]] = puzzle.missingValues

    val allCombosForPuzzle = Map[Int, List[List[Int]]]()

    for (rcnt <- 0 until missingValues.length) {

      val missingVals : List[Int] = missingValues(rcnt)

      val currentRow : Array[Int] = puzzle.row(rcnt)
      var combinationsForRow = List[List[Int]]()
      // If there are not missing values, then there is only one combination, the original values.
      if (missingVals == List()) {
        combinationsForRow = combinationsForRow :+ currentRow.toList
      }
      else if (missingVals.length == 1) {
        // split the list at the empty value.
        val parts = currentRow.toList span (!Puzzle.emptyVal(_))
        val combination = parts._1 ::: missingVals ::: parts._2.tail
        combinationsForRow = combinationsForRow :+ combination

      }
      else {
        // Move the first index to the last location, swapping with the next.
        // Repeat for the number of values.  Gives all combinations of the line.

        var currentCombination = missingVals
        missingVals.foreach { missingValue =>
          // TODO - FIX THIS BECAUSE IT DOESN"T HANDLE THE CASE OF ONE ITEM IN THE ROW.
          for (swapcnt <- 0 until (currentCombination.length - 1)) {

            // Add the values at the front.
            var combination = currentCombination.take(swapcnt)
            // swap current values
            combination = combination :+ currentCombination(swapcnt + 1)
            combination = combination :+ currentCombination(swapcnt)
            // add values after
            combination = combination ::: currentCombination.takeRight(currentCombination.length - (swapcnt + 2))

            // create a list that includes the combinations and the actual values in the right position.
            // this results in completely filled out rows, so they don't have to be calcuated later.
            var finalCombination = List[Int]()
            var tmpCombination = combination.drop(0) // seems like there should be a better way to copy.

            for (ccnt <- 0 until currentRow.length) {
              if (!Puzzle.emptyVal(currentRow(ccnt))) {
                finalCombination = finalCombination :+ currentRow(ccnt)
              }
              else {
                finalCombination = finalCombination :+ tmpCombination(0)
                tmpCombination = tmpCombination.drop(1)
              }
            }
            // add the combination to the list of combinations.
            combinationsForRow = combinationsForRow :+ finalCombination
            currentCombination = combination
          }
        }
      }
      // Add the combonations for the current row.
      allCombosForPuzzle += (rcnt -> combinationsForRow)
    }

    allCombosForPuzzle
  }

  /**
   * Tries the rest of the solution with the current puzzle and solution.
   * @param combinations All possible combinations to try to solve the puzzle.
   * @param solution The solution to the puzzle.
   * @return true if solved and the solution.
   */
  private def findSolution (combinations : Map[Int, List[List[Int]]]) : Puzzle = {

    val solution = new Puzzle

    var maxSize : Long = 1
    var startTime = new Date().getTime // start time used to calculate elapsed and remaining.
    if (showStatus) {
      for (cnt <- 0 until 9) {
        maxSize = maxSize * combinations(cnt).length
      }

      // Show the maximum number of combination to check.
      println (maxSize + " possible combinations to check.")
    }

    var tries = 0

    // Seriously brute force - using knowledge that there are nine rows.
    var solved = false
    for (comboCnt0 <- 0 until combinations(0).length; if !solved) {
      solution.setRowFromList(0)(combinations(0)(comboCnt0))
      for (comboCnt1 <- 0 until combinations(1).length; if !solved) {
        solution.setRowFromList(1)(combinations(1)(comboCnt1))
        for (comboCnt2 <- 0 until combinations(2).length; if !solved) {
          solution.setRowFromList(2)(combinations(2)(comboCnt2))
          for (comboCnt3 <- 0 until combinations(3).length; if !solved) {
            solution.setRowFromList(3)(combinations(3)(comboCnt3))
            for (comboCnt4 <- 0 until combinations(4).length; if !solved) {
              solution.setRowFromList(4)(combinations(4)(comboCnt4))
              for (comboCnt5 <- 0 until combinations(5).length; if !solved) {
                solution.setRowFromList(5)(combinations(5)(comboCnt5))
                for (comboCnt6 <- 0 until combinations(6).length; if !solved) {
                  solution.setRowFromList(6)(combinations(6)(comboCnt6))
                  for (comboCnt7 <- 0 until combinations(7).length; if !solved) {
                    solution.setRowFromList(7)(combinations(7)(comboCnt7))
                    for (comboCnt8 <- 0 until combinations(8).length; if !solved) {
                      if (showStatus) {
                        // keep track of the number of tries and the time elapsed to calculate the remaining time.
                        tries += 1
                        if (tries % 10000 == 0) {
                          // determine elapsed time.  Remaining time is number of combos times average per combo checked.
                          val secsPerTry : Double = (new Date().getTime - startTime) / 1000.0 / tries
                          printf ("%d max combinations and less than %6.2f secs remaining.\n", (maxSize - tries),((maxSize - tries) * secsPerTry))
                        }
                      }
                      solution.setRowFromList(8)(combinations(8)(comboCnt8))
                      solved = Verify(solution)
                    }
                  }
                }
              }
            }
          }
        }
      }
    }

    solution
  }

}