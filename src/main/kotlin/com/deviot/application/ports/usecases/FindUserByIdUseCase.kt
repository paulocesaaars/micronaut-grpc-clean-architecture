package com.deviot.application.ports.usecases

import com.deviot.application.dtos.UserDto

interface FindUserByIdUseCase {
    fun execute(id: String): UserDto
}