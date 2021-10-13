package io.redspark.candidatos.modules.jobtitle.service

import io.redspark.candidatos.models.dtos.JobTitleDTO
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface JobTitleService {

    fun getJobTitleList(): List<JobTitleDTO>
    fun searchJobTitle(term: String?, pageable: Pageable): Page<JobTitleDTO>
    fun getJobTitle(id: Long): JobTitleDTO
    fun createJobTitle(jobTitleDTO: JobTitleDTO): JobTitleDTO
    fun updateJobTitle(jobTitleDTO: JobTitleDTO): JobTitleDTO

}