package day2.part1

import day2.PasswordDetails
import day2.PasswordPolicy

class InvocationCountInRangePolicy : PasswordPolicy {

    override fun isValid(text: String, passwordDetails: PasswordDetails): Boolean = with(passwordDetails) {
        text.count { it == expectedChar } in firstValue..secondValue
    }
}