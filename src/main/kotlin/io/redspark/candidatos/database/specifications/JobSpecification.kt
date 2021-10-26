package io.redspark.candidatos.database.specifications

import io.redspark.candidatos.database.entities.Job
import io.redspark.candidatos.models.enums.JobStatus
import org.springframework.data.jpa.domain.Specification

class JobSpecification {

    fun searchJob(term: String?): Specification<Job>? = term?.let {
        Specification { root, query, criteriaBuilder ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("jobDescription")), "%${term.lowercase()}%")
        }

    }

    fun filterStatus(status: JobStatus?): Specification<Job>? = status?.let {
        Specification { root, query, criteriaBuilder ->
            criteriaBuilder.equal(root.get<JobStatus>("status"), status)
        }

    }



}