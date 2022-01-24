package com.deviot.application.ports.usecases

import com.deviot.application.dtos.UserDto

interface UpdateUserUseCase {
    fun execute(value: UserDto): UserDto
}