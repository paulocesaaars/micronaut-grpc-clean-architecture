package com.deviot.domain.valueobjects.common

import com.deviot.domain.exceptions.ObjectValidationException
import java.util.*

class Id {

    val value: UUID

    companion object {
        private const val INVALID_ID: String = "Id inválido"
    }

    constructor(value: String){
        try {
            this.value = UUID.fromString(value)
        }
        catch (exception: Exception) {
            throw ObjectValidationException(INVALID_ID)
        }
    }
}