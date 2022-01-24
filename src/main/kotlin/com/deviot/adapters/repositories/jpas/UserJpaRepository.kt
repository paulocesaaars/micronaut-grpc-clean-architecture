package com.deviot.adapters.repositories.jpas

import com.deviot.adapters.repositories.entities.UserEntity
import com.deviot.adapters.repositories.queries.UserQuery
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import java.util.*
import javax.persistence.EntityManager
import javax.transaction.Transactional

@Repository
abstract class UserJpaRepository(
    private val entityManager: EntityManager
): JpaRepository<UserEntity, UUID> {

    abstract fun findByEmailIgnoreCase(email: String): Optional<UserEntity>

    @Transactional
    fun checkEmailExists(email: String): Boolean {
        val query = UserQuery.checkEmaillQuery()
        return entityManager.createQuery(query)
            .setParameter("email", email)
            .singleResult as Boolean
    }
}