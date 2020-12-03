package day3.part1

import day3.main.Position
import readItems

const val FILEPATH = "src/day3/main/input.txt"

fun main() {
    val inputs: List<CharArray> = readItems(FILEPATH).map { it.toCharArray() }
    val maxWidth = inputs[0].size
    var currentPosition = Position()
    var treeCount = 0

    do {
        treeCount += if (inputs[currentPosition.row][currentPosition.column] == '#') 1 else 0
        currentPosition = Position(row = currentPosition.row + 1, column = (currentPosition.column + 3) % maxWidth)
    } while (currentPosition.row < inputs.size)

    println("Count: $treeCount")
}