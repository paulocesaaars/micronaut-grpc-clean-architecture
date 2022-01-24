package com.deviot.adapters.repositories.entities

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "tb_user")
class UserEntity(
    @Id
    var id: UUID,

    @Column(nullable = false)
    var fullName: String,

    @Column(nullable = false, unique = true)
    var email: String

) {

}
