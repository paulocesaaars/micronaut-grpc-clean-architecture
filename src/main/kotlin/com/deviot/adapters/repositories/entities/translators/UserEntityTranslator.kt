package com.deviot.adapters.repositories.entities.translators

import com.deviot.adapters.repositories.entities.UserEntity
import com.deviot.application.base.TranslatorBase
import com.deviot.domain.User
import com.deviot.domain.valueobjects.common.Id
import com.deviot.domain.valueobjects.user.Email
import com.deviot.domain.valueobjects.user.FullName

class UserEntityTranslator: TranslatorBase<UserEntity, User>() {
    companion object {
        fun fromEntity(value: UserEntity): User =
            UserEntityTranslator().fromIn(value)

        fun toEntity(value: User): UserEntity =
            UserEntityTranslator().toOut(value)

        fun fromEntities(values: List<UserEntity>): List<User> =
            UserEntityTranslator().fromInList(values)

        fun toEntities(values: List<User>): List<UserEntity> =
            UserEntityTranslator().toOutList(values)
    }

    override fun fromIn(value: UserEntity): User {
        return User(
            Id(value.id.toString()),
            FullName(value.fullName),
            Email( value.email)
        )
    }

    override fun toOut(value: User): UserEntity {
        return UserEntity(
            value.id.value,
            value.fullName.value,
            value.email.value
        )
    }
}