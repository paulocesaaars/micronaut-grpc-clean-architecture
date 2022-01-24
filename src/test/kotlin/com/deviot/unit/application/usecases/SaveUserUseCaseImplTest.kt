package com.deviot.unit.application.usecases

import com.deviot.application.exceptions.UserValidationException
import com.deviot.application.ports.repositories.UserRepository
import com.deviot.application.usecases.SaveUserUseCaseImpl
import com.deviot.fakes.domains.UserMock
import com.deviot.fakes.dtos.NewUserDtoMock
import com.deviot.fakes.dtos.UserDtoMock
import com.deviot.domain.User
import com.deviot.unit.base.TestBase
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable
import org.mockito.Mockito

internal class SaveUserUseCaseImplTest : TestBase() {

    private val userRepository = Mockito.mock(UserRepository::class.java)
    private val useCase = SaveUserUseCaseImpl(log, userRepository)

    @Test
    fun `Execute SaveUserUseCase should return of new user`() {
        val user = UserMock.getUser1()
        val newUser = NewUserDtoMock.getUser1()
        val expected = UserDtoMock.getUser1()

        Mockito.`when`(userRepository.save(any(User::class.java))).thenReturn(user)
        Mockito.`when`(userRepository.checkEmailExists(any(String::class.java))).thenReturn(false)

        var result = useCase.execute(newUser)

        Assertions.assertEquals(result.toString(), expected.toString())
    }

    @Test
    fun `Execute SaveUserUseCase should return an exception of type email is being used`() {
        val newUser = NewUserDtoMock.getUser1()
        newUser.email = ""

        Mockito.`when`(userRepository.checkEmailExists(any(String::class.java))).thenReturn(true)

        Assertions.assertThrows(
            UserValidationException::class.java,
            Executable {
                useCase.execute(newUser)
            }, "O email paulocesaaars@gmail.com já está sendo utilizado"
        )
    }

    @Test
    fun `Execute SaveUserUseCase should return an exception of type UserValidationException`() {
        val newUser = NewUserDtoMock.getUser1()
        newUser.email = ""

        Assertions.assertThrows(
            UserValidationException::class.java,
            Executable {
                useCase.execute(newUser)
            }, "Email inválido (user@email.com)"
        )
    }

    @Test
    fun `Execute SaveUserUseCase should return an exception`() {
        val newUser = NewUserDtoMock.getUser1()

        Mockito.`when`(userRepository.checkEmailExists(any(String::class.java))).thenThrow()

        Assertions.assertThrows(
            Exception::class.java,
            Executable {
                useCase.execute(newUser)
            }
        )
    }
}