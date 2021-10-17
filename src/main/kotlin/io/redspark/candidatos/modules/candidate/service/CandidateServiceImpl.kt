package io.redspark.candidatos.modules.candidate.service

import io.redspark.candidatos.config.logger.LoggerDelegate
import io.redspark.candidatos.database.entities.Candidate
import io.redspark.candidatos.database.entities.Skill
import io.redspark.candidatos.database.repositories.CandidateRepository
import io.redspark.candidatos.database.repositories.SkillRepository
import io.redspark.candidatos.models.dtos.*
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

//TODO - alterar chamada countByName para existsById
//TODO - salvar dados na tabela stage pegando id do candidato
@Service
class CandidateServiceImpl(
    private val candidatesRepository: CandidateRepository,
    private val skillsRepository: SkillRepository,
//    private val stageRepository: StageRepository

) : CandidateService() {
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
        val candidateDTO = CandidateDTO(createCandidateDTO)
        val candidate = Candidate(candidateDTO)
        candidate.skillList=skillsRepository.findAllById(createCandidateDTO.skillList)
        candidate.createdDate = LocalDateTime.now()
        val savedCandidate = candidatesRepository.save(candidate)
        return CandidateDTO(savedCandidate, skillEntityToSkillDTO(candidate.skillList))
    }

    override fun updateCandidate(id: UUID, UpdateCandidateDTO: UpdateCandidateDTO) {
        candidatesRepository.findById(id)
            .map { candidate ->
                candidate.name = UpdateCandidateDTO.name
                candidate.email = UpdateCandidateDTO.email
                candidate.phone = UpdateCandidateDTO.phone
                candidate.linkedin = UpdateCandidateDTO.linkedin
                candidate.curriculum = UpdateCandidateDTO.curriculum
                candidate.source = UpdateCandidateDTO.source
                candidate.skillList = skillsRepository.findAllById(UpdateCandidateDTO.skillList)
                candidatesRepository.save(candidate)
            }

    }
}




