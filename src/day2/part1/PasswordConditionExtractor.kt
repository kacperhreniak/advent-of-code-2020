package day2.part1

class PasswordConditionExtractor {
    private val rangeExtractor = RangeExtractor()

    fun extract(data: String): PasswordDetails {
        val separatedData = data.split(EXPECTED_TEXT_SEPARTOR)
        val expectedText = separatedData[EXPECTED_TEXT_INDEX]
        val rangeData = rangeExtractor.extract(separatedData[RANGE_INDEX])

        return PasswordDetails(
            rangeDetials = rangeData,
            text = expectedText
        )
    }

    private class RangeExtractor {
        fun extract(data: String): RangeDetials = data.split(RANGE_SEPARATOR).run {
            RangeDetials(
                minValue = get(MIN_VALUE_INDEX).toInt(),
                maxValue = get(MAX_VALUE_INDEX).toInt()
            )
        }

        private companion object {
            const val MIN_VALUE_INDEX = 0
            const val MAX_VALUE_INDEX = 1
            const val RANGE_SEPARATOR = "-"
        }
    }

    private companion object {
        const val RANGE_INDEX = 0
        const val EXPECTED_TEXT_SEPARTOR = " "
        const val EXPECTED_TEXT_INDEX = 1
    }
}