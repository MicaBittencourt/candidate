package io.redspark.candidatos.database.repositories

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface Candidate : JpaRepository<Candidate, UUID>