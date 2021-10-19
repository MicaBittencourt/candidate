package io.redspark.candidatos.database.repositories

import io.redspark.candidatos.database.entities.Candidate
import io.redspark.candidatos.database.entities.Skill
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import java.util.*

// TODO - passar Id para long, alterar busca da skill existsById
interface SkillRepository : JpaRepository<Skill, Long> {

}