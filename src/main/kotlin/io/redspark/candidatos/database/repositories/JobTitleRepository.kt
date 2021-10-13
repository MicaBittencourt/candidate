package io.redspark.candidatos.database.repositories

import io.redspark.candidatos.database.entities.JobTitle
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface JobTitleRepository : JpaRepository<JobTitle, Long> {

    fun findAllByNameContainingIgnoreCase(term: String, pageable: Pageable): Page<JobTitle>

}