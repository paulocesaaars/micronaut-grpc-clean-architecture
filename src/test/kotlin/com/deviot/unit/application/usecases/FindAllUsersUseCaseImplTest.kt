package com.deviot.unit.application.usecases

import com.deviot.application.ports.repositories.UserRepository
import com.deviot.application.usecases.FindAllUsersUseCaseImpl
import com.deviot.fakes.domains.UserMock
import com.deviot.fakes.dtos.UserDtoMock
import com.deviot.domain.PageInfo
import com.deviot.domain.valueobjects.pageinfo.PageNumber
import com.deviot.domain.valueobjects.pageinfo.PageSize
import com.deviot.unit.base.TestBase
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable
import org.mockito.Mockito

internal class FindAllUsersUseCaseImplTest: TestBase() {

    private val userRepository = Mockito.mock(UserRepository::class.java)
    private val useCase = FindAllUsersUseCaseImpl(log, userRepository)

    @Test
    fun `Execute FindAllUsersUseCase should return list of users`() {
        val pageInfo = PageInfo(PageNumber(0), PageSize(1000))
        val users = UserMock.getUsers()
        val expected = UserDtoMock.getUsers()

        Mockito.`when`(userRepository.findAll(any(PageInfo::class.java))).thenReturn(users)

        val result = useCase.execute(pageInfo)

        for (index in 0..1){
            Assertions.assertEquals(result[index].toString(), expected[index].toString())
        }
    }

    @Test
    fun `Execute FindAllUsersUseCase should return an exception Exception`() {
        val pageInfo = PageInfo(PageNumber(0),PageSize(10))

        Mockito.`when`(userRepository.findAll(pageInfo)).thenThrow()

        Assertions.assertThrows(
            Exception::class.java,
            Executable {
                useCase.execute(pageInfo)
            }
        )
    }
}