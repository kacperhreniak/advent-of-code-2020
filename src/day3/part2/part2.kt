package day3.part2

import day3.main.Position
import readItems
import java.math.BigInteger

private const val FILEPATH = "src/day3/main/input.txt"

fun main() {
    val inputs: List<CharArray> = readItems(FILEPATH).map { it.toCharArray() }
    val maxWidth = inputs[0].size
    val treesCounts: MutableList<Int> = mutableListOf()
    val strategies = readStrategies()

    for (strategy in strategies) {
        var treeCount = 0
        var currentPosition = Position()
        do {
            treeCount += if (inputs[currentPosition.row][currentPosition.column] == '#') 1 else 0
            currentPosition = currentPosition.findNextPosition(maxWidth, strategy)
        } while (currentPosition.row < inputs.size)
        treesCounts.add(treeCount)
    }

    val result: BigInteger = treesCounts.fold(BigInteger.ONE) { result: BigInteger, item: Int ->
        result * item.toBigInteger()
    }

    println("Count: $result")
}

private fun Position.findNextPosition(maxWidth: Int, strategy: MoveStrategy): Position =
    Position(row = row + strategy.down, column = (column + strategy.right) % maxWidth)

private data class MoveStrategy(val right: Int, val down: Int)

private fun readStrategies(): List<MoveStrategy> = listOf(
    MoveStrategy(right = 1, down = 1),
    MoveStrategy(right = 3, down = 1),
    MoveStrategy(right = 5, down = 1),
    MoveStrategy(right = 7, down = 1),
    MoveStrategy(right = 1, down = 2),
)