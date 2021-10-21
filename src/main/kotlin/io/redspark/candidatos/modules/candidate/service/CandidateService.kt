package io.redspark.candidatos.modules.candidate.service

import io.redspark.candidatos.models.dtos.CandidateDTO
import io.redspark.candidatos.models.dtos.CreateCandidateDTO
import io.redspark.candidatos.models.dtos.UpdateCandidateDTO
import java.util.*

interface CandidateService {
    fun getCandidateList(): List<CandidateDTO>
    fun createCandidate(createCandidateDTO: CreateCandidateDTO): CandidateDTO
    fun getCandidate(id: UUID): CandidateDTO
    fun updateCandidate(updateCandidateDTO: UpdateCandidateDTO): CandidateDTO


}
