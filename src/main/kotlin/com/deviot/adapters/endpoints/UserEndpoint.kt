package com.deviot.adapters.endpoints

import com.deviot.*
import com.deviot.adapters.endpoints.translators.UserProtoTranslator
import com.deviot.application.ports.usecases.FindAllUsersUseCase
import com.deviot.application.ports.usecases.FindUserByIdUseCase
import com.deviot.application.ports.usecases.SaveUserUseCase
import com.deviot.application.ports.usecases.UpdateUserUseCase
import com.deviot.domain.PageInfo
import com.deviot.domain.valueobjects.pageinfo.PageNumber
import com.deviot.domain.valueobjects.pageinfo.PageSize
import io.micronaut.grpc.annotation.GrpcService
import io.micronaut.tracing.annotation.ContinueSpan
import io.micronaut.tracing.annotation.SpanTag

@GrpcService
open class UserEndpoint(
    private val findAllUsersUseCase: FindAllUsersUseCase,
    private val findUserByIdUseCase: FindUserByIdUseCase,
    private val saveUserUseCase: SaveUserUseCase,
    private val updateUserUseCase: UpdateUserUseCase
): UserServiceGrpcKt.UserServiceCoroutineImplBase() {

    @ContinueSpan
    override suspend fun findAll(@SpanTag request: UserPageRequest): UsersResponse {

        val pageInfo = PageInfo(PageNumber(request.pageNumber), PageSize(request.pageSize))
        val users = findAllUsersUseCase.execute(pageInfo)

        return UserProtoTranslator.toUsersResponse(users)
    }

    override suspend fun findById(request: UserIdRequest): UserResponse {
        val user = findUserByIdUseCase.execute(
            UserProtoTranslator.fromUserIdRequest(request))

        return UserProtoTranslator.toUserResponse(user)
    }

    override suspend fun save(request: NewUserRequest): UserResponse {
        val user = saveUserUseCase.execute(
            UserProtoTranslator.fromNewUserRequest(request))

        return UserProtoTranslator.toUserResponse(user)
    }

    override suspend fun update(request: UserRequest): UserResponse {
        val user = updateUserUseCase.execute(
            UserProtoTranslator.fromUserRequest(request))

        return UserProtoTranslator.toUserResponse(user)
    }
}