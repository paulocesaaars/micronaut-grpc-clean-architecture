package com.deviot.domain

import com.deviot.domain.valueobjects.common.Id
import com.deviot.domain.valueobjects.user.Email
import com.deviot.domain.valueobjects.user.FullName

data class User(val id: Id, val fullName: FullName, val email: Email) {

    override fun toString(): String {
        return StringBuilder()
            .appendLine("${id::class.java.name}: ${id.value}")
            .appendLine("${fullName::class.java.name}: ${fullName.value}")
            .appendLine("${email::class.java.name}: ${email.value}")
            .toString()
    }
}