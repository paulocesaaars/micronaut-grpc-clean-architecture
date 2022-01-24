package com.deviot.fakes.dtos

import com.deviot.application.dtos.NewUserDto

class NewUserDtoMock {

    companion object{
        fun getUser1(): NewUserDto {
            return NewUserDto(
                "Paulo César de Souza",
                "paulocesaaars@gmail.com"
            )
        }

        fun getUser2(): NewUserDto {
            return NewUserDto(
                "Bruna Stefano Marques",
                "bruna@gmail.com"
            )
        }

        fun getUsers() : List<NewUserDto> {
            val users = mutableListOf<NewUserDto>()
            users.add(getUser1())
            users.add(getUser2())

            return users
        }
    }
}