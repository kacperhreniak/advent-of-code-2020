package day4

private const val FILEPATH = "src/day4/input.txt"
private val passportElements = listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")

fun main() {
    val validPassports = loadPassports(FILEPATH).filter { isPassportValid(it) }
    println("Valid passport Count: ${validPassports.size}")
}

private fun isPassportValid(passport: String): Boolean = passportElements.all { passport.contains(it) }