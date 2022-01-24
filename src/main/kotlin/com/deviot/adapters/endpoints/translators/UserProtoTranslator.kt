package com.deviot.adapters.endpoints.translators

import com.deviot.*
import com.deviot.application.dtos.NewUserDto
import com.deviot.application.dtos.UserDto
import com.deviot.domain.PageInfo
import com.deviot.domain.valueobjects.pageinfo.PageNumber
import com.deviot.domain.valueobjects.pageinfo.PageSize

class UserProtoTranslator {
    companion object {

        fun fromUserPageRequest(value: UserPageRequest) : PageInfo {
            return PageInfo(
                PageNumber( value.pageNumber),
                PageSize( value.pageSize)
            )
        }

        fun fromUserIdRequest(value: UserIdRequest) : String {
            return value.id
        }

        fun fromUserRequest(value: UserRequest) : UserDto {
            return UserDto(
                value.id,
                value.fullName,
                value.email
            )
        }

        fun fromNewUserRequest(value: NewUserRequest) : NewUserDto {
            return NewUserDto(
                value.fullName,
                value.email
            )
        }

        fun toUserResponse(value: UserDto): UserResponse {
            return UserResponse.newBuilder()
                .setId(value.id)
                .setFullName(value.fullName)
                .setEmail(value.email)
                .build()
        }

        fun toUsersResponse(values: List<UserDto>): UsersResponse {
            val usersResponse = UsersResponse.newBuilder()
            values.forEach() {
                usersResponse.addUsers(
                    toUserResponse(it)
                )
            }

            return usersResponse.build()
        }
    }
}