package com.deviot.application.dtos.translators

import com.deviot.application.base.TranslatorBase
import com.deviot.application.dtos.NewUserDto
import com.deviot.domain.User
import com.deviot.domain.valueobjects.common.Id
import com.deviot.domain.valueobjects.user.Email
import com.deviot.domain.valueobjects.user.FullName
import java.util.*

class NewUserDtoTranslator: TranslatorBase<NewUserDto, User>() {

    companion object {
        fun fromDto(value: NewUserDto): User = NewUserDtoTranslator().fromIn(value)
        fun toDto(value: User): NewUserDto = NewUserDtoTranslator().toOut(value)
        fun fromDtos(values: List<NewUserDto>): List<User> = NewUserDtoTranslator().fromInList(values)
        fun toDtos(values: List<User>): List<NewUserDto> = NewUserDtoTranslator().toOutList(values)
    }

    override fun fromIn(value: NewUserDto): User {
        return User(
            Id(UUID.randomUUID().toString()),
            FullName(value.fullName),
            Email(value.email),
        )
    }

    override fun toOut(value: User): NewUserDto {
        return NewUserDto(
            value.fullName.value,
            value.email.value
        )
    }
}