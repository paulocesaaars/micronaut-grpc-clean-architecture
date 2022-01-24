package com.deviot.application.ports.repositories

import com.deviot.domain.PageInfo
import com.deviot.domain.User
import com.deviot.domain.valueobjects.common.Id
import java.util.*

interface UserRepository {
    fun checkEmailExists(email: String): Boolean
    fun findAll(pageInfo: PageInfo): List<User>
    fun findById(id: Id): Optional<User>
    fun findByEmail(email: String): Optional<User>
    fun save(user: User): User
    fun totalRegisters(): Number
    fun update(user: User): User
}