package io.redspark.candidatos.database.repositories

import io.redspark.candidatos.database.entities.Job
import io.redspark.candidatos.database.entities.JobTitle
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import java.time.LocalDateTime

interface JobRepository : JpaRepository<Job, Long> {

    @Modifying
    @Query("""
        UPDATE Job 
            SET endDate = :endDate
            WHERE id = :id
    """)
    fun updateEndDate(id: Long, endDate: LocalDateTime)

}