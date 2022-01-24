package com.deviot.functional

import com.deviot.UserPageRequest
import com.deviot.UserServiceGrpcKt
import com.deviot.fakes.response.UsersResponseMock
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

@MicronautTest
class UserEndpointTest {

    @Inject
    lateinit var client: UserServiceGrpcKt.UserServiceCoroutineStub

    private fun getUserPageRequest(pageNumber: Int, pageSize: Int): UserPageRequest {
        return UserPageRequest.newBuilder()
            .setPageNumber(pageNumber)
            .setPageSize(pageSize)
            .build()
    }

    @Test
    fun `Execute FindAll and return list of users`() {

        val request = getUserPageRequest(0, 1000)
        val expected = UsersResponseMock.getUsersResponse()

        var result = runBlocking {
            client.findAll(request)
        }

        Assertions.assertEquals(result, expected)
    }
}
