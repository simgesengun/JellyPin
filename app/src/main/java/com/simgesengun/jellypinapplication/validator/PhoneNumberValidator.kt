package com.simgesengun.jellypinapplication.validator

import com.simgesengun.jellypinapplication.R

class PhoneNumberValidator {
    fun validate(phoneNumber: String?) = when {
        phoneNumber.isNullOrBlank() -> R.string.error_phone_blank
        else -> null
    }
}