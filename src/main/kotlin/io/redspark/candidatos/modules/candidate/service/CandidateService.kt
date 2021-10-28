package io.redspark.candidatos.modules.candidate.service

import io.redspark.candidatos.models.dtos.CandidateDTO
import io.redspark.candidatos.models.dtos.CreateCandidateDTO
import io.redspark.candidatos.models.dtos.UpdateCandidateDTO
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import java.util.*

interface CandidateService {
    fun getCandidatePage(pageable: Pageable): Page<CandidateDTO>
    fun createCandidate(createCandidateDTO: CreateCandidateDTO): CandidateDTO
    fun getCandidate(id: UUID): CandidateDTO
    fun updateCandidate(updateCandidateDTO: UpdateCandidateDTO): CandidateDTO
    fun searchCandidate(term: String?, pageable: Pageable): Page<CandidateDTO>


}
