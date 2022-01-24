package com.deviot.application.exceptions

import com.deviot.application.base.ValidationExceptionBase


class UserValidationException(message: String?) : ValidationExceptionBase(VALIDATION_NAME, message) {
    companion object{
        private const val VALIDATION_NAME: String = "Usuário"
    }
}