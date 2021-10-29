package io.redspark.candidatos.modules.candidate.service

import io.redspark.candidatos.config.logger.LoggerDelegate
import io.redspark.candidatos.config.logger.logCreated
import io.redspark.candidatos.config.logger.logUpdated
import io.redspark.candidatos.database.entities.Candidate
import io.redspark.candidatos.database.repositories.CandidateRepository
import io.redspark.candidatos.database.repositories.JobTitleRepository
import io.redspark.candidatos.database.repositories.SkillRepository
import io.redspark.candidatos.models.dtos.CandidateDTO
import io.redspark.candidatos.models.dtos.CandidatePageDTO
import io.redspark.candidatos.models.dtos.CandidateSaveDTO
import io.redspark.candidatos.models.errors.ServiceError
import io.redspark.candidatos.models.errors.ServiceException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class CandidateServiceImpl(
    private val candidatesRepository: CandidateRepository,
    private val skillsRepository: SkillRepository,
    private val jobTitleRepository: JobTitleRepository

) : CandidateService {
    private val logger by LoggerDelegate()

    override fun getCandidatePage(pageable: Pageable): Page<CandidatePageDTO> {
        val candidateList = candidatesRepository.findAll(pageable)
        return candidateList.map {
            return@map CandidatePageDTO(it)}
    }

    override fun createCandidate(candidateSaveDTO: CandidateSaveDTO): CandidateDTO {
        if (candidateSaveDTO.id != null) {
            throw ServiceException(ServiceError.CANDIDATE_ID_NOT_EMPTY)
        }

        val jobTitle = jobTitleRepository.findByIdOrNull(candidateSaveDTO.jobTitleId)
                ?: throw ServiceException(ServiceError.JOB_TITLE_NOT_FOUND)
        val skillList = skillsRepository.findAllById(candidateSaveDTO.skillList)
        val candidate = Candidate(candidateSaveDTO, jobTitle, skillList)

        val savedCandidate = candidatesRepository.save(candidate)
        logger.logCreated(candidate)
        return CandidateDTO(savedCandidate)
    }

    override fun updateCandidate(candidateSaveDTO: CandidateSaveDTO): CandidateDTO {
        if (candidateSaveDTO.id == null || candidatesRepository.existsById(candidateSaveDTO.id).not()) {
            throw ServiceException(ServiceError.CANDIDATE_NOT_FOUND)
        }

        val jobTitle = jobTitleRepository.findByIdOrNull(candidateSaveDTO.jobTitleId)
                ?: throw ServiceException(ServiceError.JOB_TITLE_NOT_FOUND)
        val skillList = skillsRepository.findAllById(candidateSaveDTO.skillList)
        val candidate = Candidate(candidateSaveDTO, jobTitle, skillList)

        val savedCandidate = candidatesRepository.save(candidate)
        logger.logUpdated(candidate)
        return CandidateDTO(savedCandidate)
    }

    override fun searchCandidate(term: String?, pageable: Pageable): Page<CandidateDTO> {
        val page = if (term == null) {
            candidatesRepository.findAll(pageable)
        } else {
            candidatesRepository.findAllByNameContainingIgnoreCase(term, pageable)
        }

        return page.map { CandidateDTO(it)}
    }

    override fun getCandidate(id: UUID): CandidateDTO {
        val candidate = candidatesRepository.findByIdOrNull(id)?:
        throw ServiceException(ServiceError.CANDIDATE_NOT_FOUND)
        return CandidateDTO(candidate)
    }
}