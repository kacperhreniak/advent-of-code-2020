package day2

interface PasswordPolicy {
    fun isValid(text: String, passwordDetails: PasswordDetails): Boolean
}