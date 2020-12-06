package day6.part2

import java.io.File

private const val FILEPATH = "src/day6/input.txt"
fun main() {
    val result = File(FILEPATH).readText()
        .split("\r\n\r\n")
        .map { group ->
            val groupAnswers = group.split("\r\n")
            group.replace("\r\n", "").toHashSet()
                .filter { item -> groupAnswers.all { it.contains(item) } }
        }
        .sumBy { it.size }

    println("Result: $result")
}