package io.redspark.candidatos.database.repository

import io.redspark.candidatos.database.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository: JpaRepository<User, UUID> {

}