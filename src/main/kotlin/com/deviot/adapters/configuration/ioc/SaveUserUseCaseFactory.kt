package com.deviot.adapters.configuration.ioc

import com.deviot.application.ports.infra.Log
import com.deviot.application.ports.repositories.UserRepository
import com.deviot.application.ports.usecases.SaveUserUseCase
import com.deviot.application.usecases.SaveUserUseCaseImpl
import io.micronaut.context.annotation.Factory
import jakarta.inject.Singleton

@Factory
class SaveUserUseCaseFactory(val log: Log, val userRepository: UserRepository){
    @Singleton
    fun create(): SaveUserUseCase {
        return SaveUserUseCaseImpl(log, userRepository)
    }
}