package com.deviot.application.ports.usecases

import com.deviot.application.dtos.UserDto
import com.deviot.domain.PageInfo

interface FindAllUsersUseCase {
    fun execute(pageInfo: PageInfo): List<UserDto>
}