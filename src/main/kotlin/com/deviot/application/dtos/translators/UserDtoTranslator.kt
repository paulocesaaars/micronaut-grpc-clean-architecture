package com.deviot.application.dtos.translators

import com.deviot.application.base.TranslatorBase
import com.deviot.application.dtos.UserDto
import com.deviot.domain.User
import com.deviot.domain.valueobjects.common.Id
import com.deviot.domain.valueobjects.user.Email
import com.deviot.domain.valueobjects.user.FullName

class UserDtoTranslator: TranslatorBase<UserDto, User>() {

    companion object {
        fun fromDto(value: UserDto): User = UserDtoTranslator().fromIn(value)
        fun toDto(value: User): UserDto = UserDtoTranslator().toOut(value)
        fun fromDtos(values: List<UserDto>): List<User> = UserDtoTranslator().fromInList(values)
        fun toDtos(values: List<User>): List<UserDto> = UserDtoTranslator().toOutList(values)
    }

    override fun fromIn(value: UserDto): User {
        return User(
            Id(value.id),
            FullName(value.fullName),
            Email(value.email),
        )
    }

    override fun toOut(value: User): UserDto {
        return UserDto(
            value.id.value.toString(),
            value.fullName.value,
            value.email.value
        )
    }
}
