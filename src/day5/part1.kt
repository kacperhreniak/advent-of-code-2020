package day5

private const val FILEPATH = "src/day5/input.txt"

fun main() {
    val results = findSeatsCodes().maxOrNull()

    println("Result: $results")
}