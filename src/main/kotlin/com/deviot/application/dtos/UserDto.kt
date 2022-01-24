package com.deviot.application.dtos

class UserDto(var id: String, var fullName:String, var email:String) {

    override fun toString(): String {
        return StringBuilder()
            .appendLine("${id::class.java.name}: ${id}")
            .appendLine("${fullName::class.java.name}: ${fullName}")
            .appendLine("${email::class.java.name}: ${email}")
            .toString()
    }

}


