package com.simgesengun.jellypinapplication.validator

import com.simgesengun.jellypinapplication.R

class NameSurnameValidator {
    fun validate(nameSurname: String?) = when {
        nameSurname.isNullOrBlank() -> R.string.error_name_surname_blank
        else -> null
    }
}