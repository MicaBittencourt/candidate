package io.redspark.candidatos.database.repositories

import io.redspark.candidatos.database.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<User, UUID> {

    fun findFirstByEmail(email: String): User?

}