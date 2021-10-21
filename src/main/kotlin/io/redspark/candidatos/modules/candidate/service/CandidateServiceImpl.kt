package io.redspark.candidatos.modules.candidate.service

import io.redspark.candidatos.config.logger.LoggerDelegate
import io.redspark.candidatos.config.logger.logCreated
import io.redspark.candidatos.config.logger.logUpdated
import io.redspark.candidatos.database.entities.Candidate
import io.redspark.candidatos.database.entities.JobTitle
import io.redspark.candidatos.database.entities.Skill
import io.redspark.candidatos.database.repositories.CandidateRepository
import io.redspark.candidatos.database.repositories.SkillRepository
import io.redspark.candidatos.models.dtos.*
import io.redspark.candidatos.models.errors.ServiceError
import io.redspark.candidatos.models.errors.ServiceException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

// TODO - tratar execeçoes de campos nulos id no update
//TODO - verificar regras para cadastro de skill caso tenha responsabilidade no cadastro do candidato, com existsById
//TODO - verificar como será vinculado os dados do candidato nas etapas do processo seletivo
@Service
class CandidateServiceImpl(
    private val candidatesRepository: CandidateRepository,
    private val skillsRepository: SkillRepository

) : CandidateService {
    private val logger by LoggerDelegate()

    override fun getCandidateList(): List<CandidateDTO> {
        return candidatesRepository.findAll()
            .map { CandidateDTO(it, skillEntityToSkillDTO(it.skillList)) }
    }

    fun skillEntityToSkillDTO(skillsList: List<Skill>): List<SkillDTO> {
        var skillsDTO = mutableListOf<SkillDTO>()
        for (skill in skillsList) {
            skillsDTO.add(SkillDTO(skill))
        }
        return skillsDTO
    }

    override fun createCandidate(createCandidateDTO: CreateCandidateDTO): CandidateDTO {
        if (createCandidateDTO.id != null) {
            throw ServiceException(ServiceError.CANDIDATE_ID_NOT_EMPTY)
        }

        val candidateDTO = CandidateDTO(createCandidateDTO)
        val candidate = Candidate(candidateDTO)
        candidate.skillList=skillsRepository.findAllById(createCandidateDTO.skillList)

        val savedCandidate = candidatesRepository.save(candidate)
        return CandidateDTO(savedCandidate, skillEntityToSkillDTO(candidate.skillList))
    }

    override fun updateCandidate(updateCandidate: UpdateCandidateDTO): CandidateDTO {
        if (updateCandidate.id == null || candidatesRepository.existsById(updateCandidate.id).not()) {
            throw ServiceException(ServiceError.CANDIDATE_NOT_FOUND)
        }

        var candidate = Candidate(updateCandidate)
        candidate.skillList=skillsRepository.findAllById(updateCandidate.skillList)
        candidate = candidatesRepository.save(candidate)
        
        logger.logUpdated(candidate)

        return CandidateDTO(candidate, skillEntityToSkillDTO(candidate.skillList))
    }

    override fun getCandidate(id: UUID): CandidateDTO {
        val candidate = candidatesRepository.findByIdOrNull(id)?:
        throw ServiceException(ServiceError.CANDIDATE_NOT_FOUND)
        return CandidateDTO(candidate, skillEntityToSkillDTO(candidate.skillList))
    }
}