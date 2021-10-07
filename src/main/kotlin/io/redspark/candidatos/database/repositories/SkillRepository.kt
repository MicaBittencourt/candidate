package io.redspark.candidatos.database.repositories

import io.redspark.candidatos.database.entities.Skill
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import java.util.*

// TODO - passar Id para long, alterar busca da skill existsById
interface SkillRepository : CrudRepository<Skill, UUID> {
    //abstract fun findByNameOrNull(name: String): Skill?
     fun findByName(name: String): Skill
     fun countByName(name: String): Long

}