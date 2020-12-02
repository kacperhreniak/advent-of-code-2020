package day2.part2

import FindResultUseCase
import java.io.File


private const val FILEPATH = "src/day2/input.txt"

fun main() {
    val input = readItems(FILEPATH)
    val result = FindResultUseCase(passwordPolicy = CharAtOneOfSpecificIndexPolicy()).invoke(input)

    println("Number of valid passwords: $result")
}

private fun readItems(fileName: String): List<String> =
    File(fileName)
        .readLines()