package io.redspark.candidatos.database.repositories

import io.redspark.candidatos.database.entities.Skill
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface SkillRepository : JpaRepository<Skill, UUID>