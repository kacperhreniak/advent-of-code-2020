package day3.part1

import readItems

private const val FILEPATH = "src/day3/input.txt"

fun main() {
    val inputs: List<CharArray> = readItems(FILEPATH).map { it.toCharArray() }
    val maxWidth = inputs[0].size
    var currentPosition = Position()
    var treeCount = 0

    do {
        currentPosition = currentPosition.findNextPosition(maxWidth)
        treeCount += if (inputs[currentPosition.row][currentPosition.column] == '#') 1 else 0
    } while (currentPosition.row < inputs.size - 1)

    println("Count: $treeCount")
}

private fun Position.findNextPosition(maxWidth: Int) = Position(row = row + 1, column = (column + 3) % maxWidth)

private data class Position(val row: Int = 0, val column: Int = 0)