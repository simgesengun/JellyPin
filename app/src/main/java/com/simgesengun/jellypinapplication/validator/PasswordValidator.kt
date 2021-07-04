package com.simgesengun.jellypinapplication.validator

import com.simgesengun.jellypinapplication.R

class PasswordValidator {
    fun validate(password: String?) = when {
        password.isNullOrBlank() -> R.string.error_password_blank
        password.length < 8 -> R.string.error_password_length
        !password.containsRequiredCharacters() -> R.string.error_password_invalid
        else -> null
    }

    private fun String.containsRequiredCharacters() = containsDigit()
            && containsLowerCase()
            && containsUpperCase()
            && containsSpecialCharacter()

    private fun String.containsDigit() = any { it.isDigit() }

    private fun String.containsLowerCase() = any { it.isLowerCase() }

    private fun String.containsUpperCase() = any { it.isUpperCase() }

    private fun String.containsSpecialCharacter() = any { it.isSpecialCharacter() }

    private fun Char.isSpecialCharacter() = !isDigit() && !isLetter()
}
