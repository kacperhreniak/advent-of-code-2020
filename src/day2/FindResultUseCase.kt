import day2.PasswordDetails
import day2.PasswordPolicy
import day2.PasswordConditionExtractor

class FindResultUseCase(private val passwordPolicy: PasswordPolicy) {
    private val passwordExtractor = PasswordConditionExtractor()

    fun invoke(inputs: List<String>): Int = inputs
        .map { it.split(DETAILS_SEPARATOR).toPasswordWithDetails() }
        .filter { passwordPolicy.isValid(it.password, it.details) }
        .size

    private fun List<String>.toPasswordWithDetails(): PasswordWithDetails = PasswordWithDetails(
        password = get(PASSWORD_INDEX),
        details = passwordExtractor.extract(get(DETAILS_INDEX))
    )

    private data class PasswordWithDetails(
        val password: String,
        val details: PasswordDetails
    )

    private companion object {
        const val DETAILS_SEPARATOR = ": "
        const val DETAILS_INDEX = 0
        const val PASSWORD_INDEX = 1
    }
}