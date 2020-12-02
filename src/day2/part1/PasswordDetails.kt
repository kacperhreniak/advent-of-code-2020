package day2.part1

data class PasswordDetails(
    val rangeDetials: RangeDetials,
    val text: String,
)

data class RangeDetials(
    val minValue: Int,
    val maxValue: Int,
)