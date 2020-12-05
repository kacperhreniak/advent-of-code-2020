package day4.part2

import day4.loadPassports

private const val FILEPATH = "src/day4/input.txt"

fun main() {
    val passports = loadPassports(FILEPATH)
    val validators = listOf(
        BirthYearValidator(),
        IssueYearValidator(),
        ExpirationYearValidator(),
        HairColorValidator(),
        HeightValidator(),
        EyeColorValidator(),
        PassportIdValidator()
    )

    val validator = PassportValidator(validators = validators)
    val validatedPassports = passports.filter { validator.validate(it) }

    println("Validated passports count: ${validatedPassports.size}")
}

class PassportValidator(private val validators: List<Validator> = emptyList()) {
    fun validate(passport: String): Boolean {
        val parameters = passport.split(PARAMETERS_SEPARATOR)
        return validators.all { it.validate(parameters) }
    }

    private companion object {
        const val PARAMETERS_SEPARATOR = " "
    }
}

abstract class Validator(private val key: String) {
    fun validate(parameters: List<String>): Boolean =
        parameters.find { it.contains(key) }?.run { isValid(extract(this)) } ?: false

    private fun extract(input: String): String = input.split(KEY_VALUE_SEPARATOR)[1]

    abstract fun isValid(data: String): Boolean

    private companion object {
        const val KEY_VALUE_SEPARATOR = ":"
    }
}

class BirthYearValidator : Validator(KEY_BIRTH_YEAR) {
    override fun isValid(data: String): Boolean = data.toInt() in MIN_VALUE..MAX_VALUE

    private companion object {
        const val KEY_BIRTH_YEAR = "byr"
        const val MIN_VALUE = 1920
        const val MAX_VALUE = 2002
    }
}

class IssueYearValidator : Validator(KEY_ISSUE_YEAR) {
    override fun isValid(data: String): Boolean = data.toInt() in MIN_VALUE..MAX_VALUE

    private companion object {
        const val KEY_ISSUE_YEAR = "iyr"
        const val MIN_VALUE = 2010
        const val MAX_VALUE = 2020
    }
}

class ExpirationYearValidator : Validator(KEY_EXPIRATION_YEAR) {
    override fun isValid(data: String): Boolean = data.toInt() in MIN_VALUE..MAX_VALUE

    private companion object {
        const val KEY_EXPIRATION_YEAR = "eyr"
        const val MIN_VALUE = 2020
        const val MAX_VALUE = 2030
    }
}

class HairColorValidator : Validator(KEY_HAIR_COLOR) {

    override fun isValid(data: String): Boolean =
        data.first() == FIRST_CHAR &&
                data.drop(1).all { availableChars.contains(it) } &&
                data.length == LENGTH

    private companion object {
        const val KEY_HAIR_COLOR = "hcl"
        const val FIRST_CHAR = '#'
        const val LENGTH = 7
        val availableChars = listOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f')
    }
}

class HeightValidator : Validator(KEY_HEIGHT) {
    override fun isValid(data: String): Boolean = findUnit(data).run {
        val text: String = data.subSequence(0, data.length - 2).toString()
        text.toIntOrNull()?.let { checkHeight(it) } ?: false
    }

    private fun findUnit(data: String): Unit = if (data.takeLast(2) == CM) {
        Unit.CM
    } else {
        Unit.IN
    }

    sealed class Unit(
        private val minValue: Int,
        private val maxValue: Int
    ) {
        object IN : Unit(59, 76)
        object CM : Unit(150, 193)

        fun checkHeight(height: Int) = height in minValue..maxValue
    }

    private companion object {
        const val KEY_HEIGHT = "hgt"
        const val CM = "cm"
        const val IN = "in"
    }
}

class EyeColorValidator : Validator(KEY_EYE_COLOR) {
    override fun isValid(data: String): Boolean = COLORS.contains(data)

    private companion object {
        const val KEY_EYE_COLOR = "ecl"
        val COLORS = listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
    }
}

class PassportIdValidator : Validator(KEY_PASSPORT_ID) {

    override fun isValid(data: String): Boolean = data.length == LENGTH && data.all { DIGITS.contains(it) }

    private companion object {
        const val KEY_PASSPORT_ID = "pid"
        const val LENGTH = 9
        val DIGITS = listOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9')
    }
}