package day2.part2

import day2.PasswordDetails
import day2.PasswordPolicy

class CharAtOneOfSpecificIndexPolicy : PasswordPolicy {
    override fun isValid(text: String, passwordDetails: PasswordDetails): Boolean {
        return (text.getAtPosition(passwordDetails.firstValue) == passwordDetails.expectedChar &&
                text.getAtPosition(passwordDetails.secondValue) != passwordDetails.expectedChar) ||
                (text.getAtPosition(passwordDetails.firstValue) != passwordDetails.expectedChar &&
                        text.getAtPosition(passwordDetails.secondValue) == passwordDetails.expectedChar)
    }

    private fun String.getAtPosition(position: Int): Char = get(position - 1)
}