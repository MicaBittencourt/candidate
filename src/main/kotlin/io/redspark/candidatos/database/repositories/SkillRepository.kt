package io.redspark.candidatos.database.repositories

import io.redspark.candidatos.database.entities.Skill
import org.springframework.data.jpa.repository.JpaRepository

// TODO - passar Id para long, alterar busca da skill existsById
interface SkillRepository : JpaRepository<Skill, Long> {

}