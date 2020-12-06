package day6.part1

import java.io.File

private const val FILEPATH = "src/day6/input.txt"

fun main() {
    val result = File(FILEPATH).readText()
        .split("\r\n\r\n")
        .map {
            it.toHashSet().apply {
                remove('\r')
                remove('\n')
            }
        }
        .sumBy { it.size }

    println("Result: $result ")
}