package com.deviot.fakes.response

import com.deviot.UsersResponse

class UsersResponseMock {
    companion object{
        fun getUsersResponse() : UsersResponse {
            return  UsersResponse.newBuilder()
                .addUsers(UserResponseMock.getUserResponse1())
                .addUsers(UserResponseMock.getUserResponse2())
                .build()
        }
    }
}