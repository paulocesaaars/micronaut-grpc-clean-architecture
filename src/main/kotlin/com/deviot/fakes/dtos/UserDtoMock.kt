package com.deviot.fakes.dtos

import com.deviot.application.dtos.UserDto

class UserDtoMock {

    companion object{
        fun getUser1(): UserDto {
            return UserDto(
                "af4bf17c-fca7-453d-a9eb-251f3837da10",
                "Paulo César de Souza",
                "paulocesaaars@gmail.com"
            )
        }

        fun getUser2(): UserDto {
            return UserDto(
                "883cf9fa-f7e9-43d4-a8cb-51fd65863ba6",
                "Bruna Stefano Marques",
                "bruna@gmail.com"
            )
        }

        fun getUsers() : List<UserDto> {
            val users = mutableListOf<UserDto>()
            users.add(getUser1())
            users.add(getUser2())

            return users
        }
    }
}