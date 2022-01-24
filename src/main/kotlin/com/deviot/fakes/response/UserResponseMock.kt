package com.deviot.fakes.response

import com.deviot.UserResponse

class UserResponseMock {
    companion object{
        fun getUserResponse1() : UserResponse {
            return  UserResponse.newBuilder()
                .setId("af4bf17c-fca7-453d-a9eb-251f3837da10")
                .setFullName("Paulo César de Souza")
                .setEmail("paulocesaaars@gmail.com")
                .build()
        }

        fun getUserResponse2() : UserResponse {
            return  UserResponse.newBuilder()
                .setId("883cf9fa-f7e9-43d4-a8cb-51fd65863ba6")
                .setFullName("Bruna Stefano Marques")
                .setEmail("bruna@gmail.com")
                .build()
        }
    }
}