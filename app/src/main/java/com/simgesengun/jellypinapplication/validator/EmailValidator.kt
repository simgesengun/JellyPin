package com.simgesengun.jellypinapplication.validator

import com.simgesengun.jellypinapplication.R

class EmailValidator {
    fun validate(email: String?) = when {
        email.isNullOrBlank() -> R.string.error_email_blank
        !email.contains("@")
                || !email.contains(".") -> R.string.error_email_invalid
        else -> null
    }
}