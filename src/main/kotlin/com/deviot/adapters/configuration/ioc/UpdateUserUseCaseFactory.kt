package com.deviot.adapters.configuration.ioc

import com.deviot.application.ports.infra.Log
import com.deviot.application.ports.repositories.UserRepository
import com.deviot.application.ports.usecases.UpdateUserUseCase
import com.deviot.application.usecases.UpdateUserUseCaseImpl
import io.micronaut.context.annotation.Factory
import jakarta.inject.Singleton

@Factory
class UpdateUserUseCaseFactory(val log: Log, val userRepository: UserRepository){
    @Singleton
    fun create(): UpdateUserUseCase {
        return UpdateUserUseCaseImpl(log, userRepository)
    }
}