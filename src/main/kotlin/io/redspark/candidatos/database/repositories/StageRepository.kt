package io.redspark.candidatos.database.repositories

import io.redspark.candidatos.database.entities.Stage
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface StageRepository : JpaRepository<Stage, UUID>