package com.deviot.application.ports.usecases

import com.deviot.application.dtos.NewUserDto
import com.deviot.application.dtos.UserDto

interface SaveUserUseCase {
    fun execute(value: NewUserDto): UserDto
}