package com.deviot.unit.application.usecases

import com.deviot.application.exceptions.UserValidationException
import com.deviot.application.ports.repositories.UserRepository
import com.deviot.application.usecases.UpdateUserUseCaseImpl
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

internal class UpdateUserUseCaseImplTest : TestBase() {

    private val userRepository = Mockito.mock(UserRepository::class.java)
    private val useCase = UpdateUserUseCaseImpl(log, userRepository)

    @Test
    fun `Execute UpdateUserUseCase should return updated user`() {
        val user = UserMock.getUser1()
        val currentUser = Optional.of(UserMock.getUser1())

        val newUser = UserDtoMock.getUser1()
        val expected = UserDtoMock.getUser1()

        Mockito.`when`(userRepository.findById(any(Id::class.java))).thenReturn(currentUser)
        Mockito.`when`(userRepository.findByEmail(any(String::class.java))).thenReturn(currentUser)
        Mockito.`when`(userRepository.update(any(User::class.java))).thenReturn(user)

        var result = useCase.execute(newUser)

        Assertions.assertEquals(result.toString(), expected.toString())
    }

    @Test
    fun `Execute UpdateUserUseCase should return an exception of type email is being used`() {
        val user = UserMock.getUser1()
        val currentUser = Optional.of(UserMock.getUser1())
        val otherUser = Optional.of(UserMock.getUser2())

        val newUser = UserDtoMock.getUser1()
        newUser.email = "bruna@gmail.com"

        Mockito.`when`(userRepository.findById(any(Id::class.java))).thenReturn(currentUser)
        Mockito.`when`(userRepository.findByEmail(any(String::class.java))).thenReturn(otherUser)

        Assertions.assertThrows(
            UserValidationException::class.java,
            Executable {
                useCase.execute(newUser)
            }, "O email bruna@gmail.com já está sendo utilizado"
        )
    }

    @Test
    fun `Execute UpdateUserUseCase should return an exception of type UserValidationException`() {
        val newUser = UserDtoMock.getUser1()
        newUser.email = ""

        Assertions.assertThrows(
            UserValidationException::class.java,
            Executable {
                useCase.execute(newUser)
            }, "Email inválido (user@email.com)"
        )
    }

    @Test
    fun `Execute UpdateUserUseCase should return an exception`() {
        val newUser = UserDtoMock.getUser1()

        Mockito.`when`(userRepository.findById(any(Id::class.java))).thenThrow()

        Assertions.assertThrows(
            Exception::class.java,
            Executable {
                useCase.execute(newUser)
            }
        )
    }
}