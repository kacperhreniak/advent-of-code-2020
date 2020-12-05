package day5

import readItems

fun findSeatsCodes(filepath: String = "src/day5/input.txt") = readItems(filepath).asSequence()
    .map { findRowIndex(it.substring(0, 7)) * 8 + findColumnIndex(it.substring(7, 10)) }

private fun findRowIndex(code: String): Int = code.asIterable()
    .fold(Pair(0, 127)) { range: Pair<Int, Int>, symbol: Char ->
        findRange(condition = symbol == 'F', range = range)
    }.first

private fun findColumnIndex(code: String): Int = code.asIterable()
    .fold(Pair(0, 7)) { range: Pair<Int, Int>, symbol: Char ->
        findRange(condition = symbol == 'L', range = range)
    }.first

private fun findRange(condition: Boolean, range: Pair<Int, Int>): Pair<Int, Int> = if (condition) {
    Pair(first = range.first, second = (range.first + range.second) / 2)
} else {
    Pair(first = ((range.first + range.second) / 2) + 1, second = range.second)
}
