package io.redspark.candidatos.database.repositories

import io.redspark.candidatos.database.entities.Job
import io.redspark.candidatos.models.enums.JobStatus
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.time.LocalDateTime

interface JobRepository : JpaRepository<Job, Long>, JpaSpecificationExecutor<Job> {

    @Modifying
    @Query("""
        UPDATE Job 
            SET endDate = :endDate, status = :status
            WHERE id = :id
    """)
    fun updateEndDate(id: Long, endDate: LocalDateTime, status: JobStatus)

    @Query("""
        SELECT p FROM Job p 
        WHERE p.status = :status
        """)
    fun findByStatus(@Param("status") status: JobStatus, pageable: Pageable): Page<Job>

}