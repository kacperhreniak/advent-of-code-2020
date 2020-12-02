package day1.part2

import java.io.File

private const val EXPECTED_SUM = 2020
private const val FILENAME = "src/day1/main/input.txt"

fun main() {
    val inputs = readItems(FILENAME)
    val requiredItems = findFirstTwoItemsSumTo2020(inputs)
    val result = calculateResult(requiredItems)

    println("Result: $result")
}

private fun readItems(fileName: String): List<Int> =
    File(fileName)
        .readLines()
        .map { it.toInt() }

private fun findFirstTwoItemsSumTo2020(inputs: List<Int>): List<Int> {
    val checkInvocations: MutableList<Conditions> = mutableListOf()
    val hashInputSet = inputs.toHashSet()

    for (item in hashInputSet.iterator()) {
        checkInvocations.firstOrNull { hashInputSet.contains(it.sum - item) }
            .let { conditions ->
                if (conditions != null) {
                    return conditions.listItems.toMutableList().apply {
                        add(item)
                        add(conditions.sum - item)
                    }
                } else {
                    val searchSum = EXPECTED_SUM - item
                    checkInvocations.add(Conditions(listItems = listOf(item), sum = searchSum))
                }
            }
    }

    throw NoSuchElementException("Input does not contain required values")
}

private fun calculateResult(items: List<Int>): Int = items.foldRight(initial = 1) { result, item -> result * item }

data class Conditions(
    val listItems: List<Int>,
    val sum: Int
)