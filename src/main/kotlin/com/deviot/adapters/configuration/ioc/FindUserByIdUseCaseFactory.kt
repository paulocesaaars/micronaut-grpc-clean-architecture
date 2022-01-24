package com.deviot.adapters.configuration.ioc

import com.deviot.application.ports.infra.Log
import com.deviot.application.ports.repositories.UserRepository
import com.deviot.application.ports.usecases.FindUserByIdUseCase
import com.deviot.application.usecases.FindUserByIdUseCaseImpl
import io.micronaut.context.annotation.Factory
import jakarta.inject.Singleton

@Factory
class FindUserByIdUseCaseFactory(val log: Log, val userRepository: UserRepository){
    @Singleton
    fun create(): FindUserByIdUseCase {
        return FindUserByIdUseCaseImpl(log, userRepository)
    }
}