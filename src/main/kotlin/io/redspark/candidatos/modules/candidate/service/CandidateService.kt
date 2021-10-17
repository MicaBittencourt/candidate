package io.redspark.candidatos.modules.candidate.service

import io.redspark.candidatos.models.dtos.CandidateDTO
import io.redspark.candidatos.models.dtos.CreateCandidateDTO
import io.redspark.candidatos.models.dtos.UpdateCandidateDTO
import java.util.*

abstract class CandidateService {
    abstract fun getCandidateList(): List<CandidateDTO>
    abstract fun createCandidate(createCandidateDTO: CreateCandidateDTO): CandidateDTO
    abstract fun updateCandidate(id: UUID, UpdateCandidateDTO: UpdateCandidateDTO)


}
