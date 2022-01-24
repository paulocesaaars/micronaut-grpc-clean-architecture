package com.deviot.fakes.domains

import com.deviot.domain.User
import com.deviot.domain.valueobjects.common.Id
import com.deviot.domain.valueobjects.user.Email
import com.deviot.domain.valueobjects.user.FullName

class UserMock {
    companion object {
        fun getUser1(): User {
            return User(
                Id("af4bf17c-fca7-453d-a9eb-251f3837da10"),
                FullName("Paulo César de Souza"),
                Email("paulocesaaars@gmail.com")
            )
        }

        fun getUser2(): User {
            return User(
                Id("883cf9fa-f7e9-43d4-a8cb-51fd65863ba6"),
                FullName("Bruna Stefano Marques"),
                Email("bruna@gmail.com")
            )
        }

        fun getUsers() : List<User> {
            val users = mutableListOf<User>()
            users.add(getUser1())
            users.add(getUser2())

            return users
        }
    }
}