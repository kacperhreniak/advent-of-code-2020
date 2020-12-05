package day4

import readItems

fun loadPassports(filepath: String): List<String> {
    val passports = mutableListOf<String>()
    var passportDataBuilder = StringBuilder()

    for (line in readItems(filepath).iterator()) {
        if (line.isNotEmpty()) {
            passportDataBuilder.append(line).append(" ")
        } else {
            passports.add(passportDataBuilder.toString())
            passportDataBuilder = StringBuilder()
        }
    }
    if (passportDataBuilder.isNotEmpty()) {
        passports.add(passportDataBuilder.toString())
    }
    return passports
}