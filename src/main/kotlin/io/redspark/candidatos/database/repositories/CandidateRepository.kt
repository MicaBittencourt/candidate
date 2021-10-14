package io.redspark.candidatos.database.repositories

import io.redspark.candidatos.database.entities.Candidate
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CandidateRepository : JpaRepository<Candidate, UUID>