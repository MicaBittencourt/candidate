package io.redspark.candidatos.database.repositories

import io.redspark.candidatos.database.entities.Candidate
import io.redspark.candidatos.database.entities.JobTitle
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import java.util.*

interface CandidateRepository : CrudRepository<Candidate, UUID> {
    fun findAllByNameContainingIgnoreCase(term: String, pageable: Pageable): Page<Candidate>
    fun findAll(pageable: Pageable): Page<Candidate>


}