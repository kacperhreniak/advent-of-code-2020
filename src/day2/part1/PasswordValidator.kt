package day2.part1

class PasswordValidator {

    private val passwordConditionExtractor = PasswordConditionExtractor()

    fun isValid(data: String): Boolean {
        val separatedData = data.split(DETAILS_SEPARATOR)
        val passwordDetails = passwordConditionExtractor.extract(separatedData[DETAILS_INDEX])

        return isValid(text = separatedData[PASSWORD_INDEX], passwordDetails = passwordDetails)

    }

    private fun isValid(text: String, passwordDetails: PasswordDetails): Boolean {
        val count = text.count { it.toString() == passwordDetails.text }
        return isInvocationNumberInRange(count, passwordDetails.rangeDetials)
    }

    private fun isInvocationNumberInRange(count: Int, rangeDetials: RangeDetials): Boolean {
        return count >= rangeDetials.minValue && count <= rangeDetials.maxValue
    }


    private companion object {
        const val DETAILS_SEPARATOR = ": "
        const val DETAILS_INDEX = 0
        const val PASSWORD_INDEX = 1
    }
}