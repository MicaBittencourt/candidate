package io.redspark.candidatos.modules.candidate.service

import io.redspark.candidatos.models.dtos.CandidateDTO
import io.redspark.candidatos.models.dtos.CandidatePageDTO
import io.redspark.candidatos.models.dtos.CandidateSaveDTO
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.*

interface CandidateService {
    fun getCandidatePage(pageable: Pageable): Page<CandidatePageDTO>
    fun createCandidate(candidateSaveDTO: CandidateSaveDTO): CandidateDTO
    fun getCandidate(id: UUID): CandidateDTO
    fun updateCandidate(updateCandidateDTO: CandidateSaveDTO): CandidateDTO
    fun searchCandidate(term: String?, pageable: Pageable): Page<CandidateDTO>


}
