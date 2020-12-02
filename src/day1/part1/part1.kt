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

private fun findFirstTwoItemsSumTo2020(inputs: List<Int>): Pair<Int, Int> {
    return inputs.toHashSet().run {
        firstOrNull { contains(EXPECTED_SUM - it) }?.let { value ->
            Pair(value, EXPECTED_SUM - value)
        }
    } ?: throw NoSuchElementException("Input does not contain required values")
}

private fun calculateResult(items: Pair<Int, Int>): Int = items.first * items.second