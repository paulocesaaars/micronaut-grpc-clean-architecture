package com.deviot.application.base

abstract class ValidationExceptionBase(validationName: String, message: String?) :
    RuntimeException("$validationName: $message") {
}