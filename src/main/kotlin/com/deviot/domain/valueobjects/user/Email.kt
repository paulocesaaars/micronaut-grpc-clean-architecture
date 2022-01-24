package com.deviot.domain.valueobjects.user

import com.deviot.domain.exceptions.ObjectValidationException

class Email {

    val value: String

    private val regex: Regex =
        Regex("^([\\w-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([\\w-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$")

    companion object {
        private const val INVALID_FORMAT: String = "Email inválido (user@email.com)"
    }

    constructor(value:String){

        if(!regex.matches(value))
            throw ObjectValidationException(INVALID_FORMAT)

        this.value = value
    }
}