package io.redspark.candidatos.modules.service.candidate

import io.redspark.candidatos.models.dtos.CandidateDTO
import io.redspark.candidatos.models.dtos.CreateCandidateDTO

abstract class CandidateService {
    abstract fun getCandidateList(): List<CandidateDTO>
    abstract fun createCandidate(createCandidateDTO: CreateCandidateDTO): CandidateDTO

}
