package com.deviot.application.usecases

import com.deviot.application.base.UseCaseBase
import com.deviot.application.dtos.UserDto
import com.deviot.application.dtos.translators.UserDtoTranslator
import com.deviot.application.ports.usecases.FindAllUsersUseCase
import com.deviot.application.ports.infra.Log
import com.deviot.application.ports.repositories.UserRepository
import com.deviot.domain.PageInfo

open class FindAllUsersUseCaseImpl(
    logger: Log,
    private val userRepository: UserRepository
    ) : UseCaseBase(logger), FindAllUsersUseCase {

    override fun execute(pageInfo: PageInfo): List<UserDto> {
        try {
            val users = userRepository.findAll(pageInfo)
            return UserDtoTranslator.toDtos(users)
        }
        catch (exception: Exception)
        {
            throw genericError(exception)
        }
    }
}