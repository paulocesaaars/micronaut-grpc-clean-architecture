package com.deviot.adapters.configuration.ioc

import com.deviot.application.ports.infra.Log
import com.deviot.application.ports.repositories.UserRepository
import com.deviot.application.ports.usecases.FindAllUsersUseCase
import com.deviot.application.usecases.FindAllUsersUseCaseImpl
import io.micronaut.context.annotation.Factory
import jakarta.inject.Singleton

@Factory
class FindAllUsersUseCaseFactory(val log: Log, val userRepository: UserRepository) {
    @Singleton
    fun create(): FindAllUsersUseCase {
        return FindAllUsersUseCaseImpl(log, userRepository)
    }
}
