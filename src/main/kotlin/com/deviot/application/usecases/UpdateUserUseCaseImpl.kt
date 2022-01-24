package com.deviot.application.usecases

import com.deviot.application.base.UseCaseBase
import com.deviot.application.dtos.UserDto
import com.deviot.application.dtos.translators.UserDtoTranslator
import com.deviot.application.exceptions.NotFoundRegisterException
import com.deviot.application.exceptions.UserValidationException
import com.deviot.application.ports.usecases.UpdateUserUseCase
import com.deviot.application.ports.infra.Log
import com.deviot.application.ports.repositories.UserRepository
import com.deviot.domain.exceptions.ObjectValidationException

open class UpdateUserUseCaseImpl(
    logger: Log,
    private val userRepository: UserRepository
    ): UseCaseBase(logger),  UpdateUserUseCase {

    companion object {
        private const val EMAIL_REPLACE = ":email"
        private const val EMAIL_EXISTS_EXCEPTION = "O email :email já está sendo utilizado"
        private const val NOT_FOUND_MESSAGE = "Usuário não encontrado"
    }

    override fun execute(value: UserDto): UserDto {
        try {
            val user = UserDtoTranslator.fromDto(value)

            val currentUser = userRepository.findById(user.id).orElseThrow {
                NotFoundRegisterException(NOT_FOUND_MESSAGE)
            }

            val userForValidation = userRepository.findByEmail(user.email.value)
            if(userForValidation.isPresent) {
                if(userForValidation.get().id.value != currentUser.id.value) {
                    throw ObjectValidationException(
                        EMAIL_EXISTS_EXCEPTION.replace(EMAIL_REPLACE, user.email.value)
                    )
                }
            }

            val updateUser = currentUser.copy(
                fullName = user.fullName,
                email = user.email
            )

            val result = userRepository.update(updateUser)

            return UserDtoTranslator.toDto(result)
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
