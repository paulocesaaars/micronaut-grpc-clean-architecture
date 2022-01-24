package com.deviot.unit.application.usecases

import com.deviot.application.exceptions.NotFoundRegisterException
import com.deviot.application.exceptions.UserValidationException
import com.deviot.application.ports.repositories.UserRepository
import com.deviot.application.usecases.FindUserByIdUseCaseImpl
import com.deviot.fakes.domains.UserMock
import com.deviot.fakes.dtos.UserDtoMock
import com.deviot.domain.User
import com.deviot.domain.valueobjects.common.Id
import com.deviot.unit.base.TestBase
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable
import org.mockito.Mockito
import java.util.*

internal class FindUserByIdUseCaseImplTest : TestBase() {

    private val userRepository = Mockito.mock(UserRepository::class.java)
    private val useCase = FindUserByIdUseCaseImpl(log, userRepository)

    @Test
    fun `Execute FindUserByIdUseCase should return list of user`() {
        val id = "af4bf17c-fca7-453d-a9eb-251f3837da10"
        val user = Optional.of(UserMock.getUser1())
        val expected = UserDtoMock.getUser1()

        Mockito.`when`(userRepository.findById(any(Id::class.java))).thenReturn(user)

        var result = useCase.execute(id)

        Assertions.assertEquals(result.toString(), expected.toString())
    }

    @Test
    fun `Execute FindUserByIdUseCase should return an exception of type NotFoundRegisterException`() {
        val id = "af4bf17c-fca7-453d-a9eb-251f3837da10"
        val user = Optional.empty<User>()

        Mockito.`when`(userRepository.findById(any(Id::class.java))).thenReturn(user)

        Assertions.assertThrows(
            NotFoundRegisterException::class.java,
            Executable {
                useCase.execute(id)
            }
        )
    }

    @Test
    fun `Execute FindUserByIdUseCase should return an exception of type UserValidationException`() {
        val id = ""

        Assertions.assertThrows(
            UserValidationException::class.java,
            Executable {
                useCase.execute(id)
            }
        )
    }

    @Test
    fun `Execute FindUserByIdUseCase should return an exception`() {
        val id = "af4bf17c-fca7-453d-a9eb-251f3837da10"

        Mockito.`when`(userRepository.findById(any(Id::class.java))).thenThrow()

        Assertions.assertThrows(
            Exception::class.java,
            Executable {
                useCase.execute(id)
            }
        )
    }
}