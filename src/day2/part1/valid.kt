import day2.part1.PasswordValidator
import java.io.File

private const val FILEPATH = "src/day2/input.txt"

fun main() {
    val passwordValidation = PasswordValidator()
    val input = readItems(FILEPATH)

    val result = input.filter { passwordValidation.isValid(it) }.size

    println("Number of valid passwords: $result")
}

private fun readItems(fileName: String): List<String> =
    File(fileName)
        .readLines()