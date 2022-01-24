package com.deviot.application.base

import com.deviot.application.exceptions.NotFoundRegisterException
import com.deviot.application.ports.infra.Log

abstract class UseCaseBase(protected val logger: Log) {

    companion object {
        const val GENERIC_ERRO_MESSAGE: String = "Não foi possível executar a requisição nesse momento."
    }

    protected fun genericError(exception: Exception): Exception {

        if(exception::class.java == NotFoundRegisterException::class.java)
            throw exception

        logger.critical(exception)
        throw Exception(GENERIC_ERRO_MESSAGE);
    }

}