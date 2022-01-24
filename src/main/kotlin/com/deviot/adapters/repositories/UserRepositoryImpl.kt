package com.deviot.adapters.repositories

import com.deviot.adapters.repositories.entities.translators.UserEntityTranslator
import com.deviot.adapters.repositories.jpas.UserJpaRepository
import com.deviot.application.ports.repositories.UserRepository
import com.deviot.domain.PageInfo
import com.deviot.domain.User
import com.deviot.domain.valueobjects.common.Id
import io.micronaut.data.model.Pageable
import jakarta.inject.Singleton
import java.util.*
import javax.persistence.EntityManager

@Singleton
class UserRepositoryImpl (
    private val entityManager: EntityManager,
    private val userJpaRepository: UserJpaRepository
    ): UserRepository {

    override fun checkEmailExists(email: String): Boolean {
        return userJpaRepository.checkEmailExists(email)
    }

    override fun findAll(pageInfo: PageInfo): List<User> {
        val pageable = Pageable.from(
            pageInfo.pageNumber.value, pageInfo.pageSize.value)

        val users = userJpaRepository.findAll(pageable)
                                                        .toList()

        return UserEntityTranslator.fromEntities(users)
    }

    override fun findById(id: Id): Optional<User> {

        val user = userJpaRepository.findById(id.value)

        if (user.isEmpty)
            return Optional.empty()

        return Optional.ofNullable(UserEntityTranslator.fromEntity(user.get()))
    }

    override fun findByEmail(email: String): Optional<User> {
        var user = userJpaRepository.findByEmailIgnoreCase(email)

        if (user.isEmpty)
            return Optional.empty()

        return Optional.ofNullable(UserEntityTranslator.fromEntity(user.get()))
    }

    override fun save(user: User): User {
        val userEntity = userJpaRepository.save(
            UserEntityTranslator.toEntity(user))

        return UserEntityTranslator.fromEntity(userEntity)
    }

    override fun totalRegisters(): Number {
        return userJpaRepository.count()
    }

    override fun update(user: User): User {
        val userEntity = userJpaRepository.update(
            UserEntityTranslator.toEntity(user))

        return UserEntityTranslator.fromEntity(userEntity)
    }
}