package com.deviot.application.usecases

import com.deviot.application.base.UseCaseBase
import com.deviot.application.dtos.UserDto
import com.deviot.application.dtos.translators.UserDtoTranslator
import com.deviot.application.exceptions.NotFoundRegisterException
import com.deviot.application.exceptions.UserValidationException
import com.deviot.application.ports.usecases.FindUserByIdUseCase
import com.deviot.application.ports.infra.Log
import com.deviot.application.ports.repositories.UserRepository
import com.deviot.domain.exceptions.ObjectValidationException
import com.deviot.domain.valueobjects.common.Id

open class FindUserByIdUseCaseImpl(
    logger: Log,
    private val userRepository: UserRepository
    ): UseCaseBase(logger),  FindUserByIdUseCase {

    companion object {
        private const val NOT_FOUND_MESSAGE = "Usuário não encontrado"
    }

    override fun execute(id: String): UserDto {
        try {
            val user = userRepository.findById(Id(id)).orElseThrow {
                NotFoundRegisterException(NOT_FOUND_MESSAGE)
            }

            return UserDtoTranslator.toDto(user)
        }
        catch (exception: ObjectValidationException) {
            throw UserValidationException(exception.message)
        }
        catch (exception: Exception)
        {
            throw genericError(exception)
        }
    }
}