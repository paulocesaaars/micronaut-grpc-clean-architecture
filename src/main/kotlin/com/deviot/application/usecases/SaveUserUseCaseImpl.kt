package com.deviot.application.usecases

import com.deviot.application.base.UseCaseBase
import com.deviot.application.dtos.NewUserDto
import com.deviot.application.dtos.UserDto
import com.deviot.application.dtos.translators.NewUserDtoTranslator
import com.deviot.application.dtos.translators.UserDtoTranslator
import com.deviot.application.exceptions.UserValidationException
import com.deviot.application.ports.infra.Log
import com.deviot.application.ports.repositories.UserRepository
import com.deviot.application.ports.usecases.SaveUserUseCase
import com.deviot.domain.exceptions.ObjectValidationException

open class SaveUserUseCaseImpl(
    logger: Log,
    private val userRepository: UserRepository
    ): UseCaseBase(logger),  SaveUserUseCase {

    companion object{
        private const val EMAIL_REPLACE = ":email"
        private const val EMAIL_EXISTS_EXCEPTION = "O email :email já está sendo utilizado"
    }

    override fun execute(value: NewUserDto): UserDto {
        try {

            val user = NewUserDtoTranslator.fromDto(value)

            val checkEmail = userRepository.checkEmailExists(user.email.value)
            if(checkEmail){
                throw ObjectValidationException(
                    EMAIL_EXISTS_EXCEPTION.replace(EMAIL_REPLACE, user.email.value)
                )
            }

            val result = userRepository.save(user)
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