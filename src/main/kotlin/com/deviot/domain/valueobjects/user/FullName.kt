package com.deviot.domain.valueobjects.user

import com.deviot.domain.exceptions.ObjectValidationException

class FullName {

    val value: String

    companion object {
        private const val INVALID_LENGTH_MIN: String = "Nome completo deve ter no mínimo 5 caracteres"
        private const val INVALID_LENGTH_MAX: String = "Nome completo deve ter no máximo 100 caracteres"
    }

    constructor(value:String){

        if(value.length < 5)
            throw ObjectValidationException(INVALID_LENGTH_MIN)

        if(value.length > 100)
            throw ObjectValidationException(INVALID_LENGTH_MAX)

        this.value = value
    }
}
