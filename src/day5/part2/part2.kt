package day5

fun main() {
    val results = findSeatsCodes()
        .sorted()
        .zipWithNext()
        .first { (first, second) -> first + 2 == second }
        .first + 1

    println("Result: $results")
}