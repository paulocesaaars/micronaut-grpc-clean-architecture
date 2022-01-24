package com.deviot.application.dtos

class NewUserDto(var fullName: String, var email: String) {

    override fun toString(): String {
        return StringBuilder()
            .appendLine("${fullName::class.java.name}: ${fullName}")
            .appendLine("${email::class.java.name}: ${email}")
            .toString()
    }

}