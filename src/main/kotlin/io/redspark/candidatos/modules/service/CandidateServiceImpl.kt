package io.redspark.candidatos.modules.service

import io.redspark.candidatos.database.entities.Candidate
import io.redspark.candidatos.database.entities.Skill
import io.redspark.candidatos.database.entities.Stage
import io.redspark.candidatos.database.repositories.CandidateRepository
import io.redspark.candidatos.database.repositories.SkillRepository
import io.redspark.candidatos.database.repositories.StageRepository
import io.redspark.candidatos.models.dtos.*
import org.springframework.stereotype.Service
import java.time.LocalDateTime

//TODO - alterar chamada countByName para existsById
@Service
class CandidateServiceImpl(
    private val candidatesRepository: CandidateRepository,
    private val skillsRepository: SkillRepository,
    private val stageRepository: StageRepository

) : CandidateService() {
    override fun getCandidateList(): List<CandidateDTO> {
        return candidatesRepository.findAll()
            .map {
                CandidateDTO(it, skillEntityToSkillDTO(it.skillList), stageEntityToStageDTO(it.stagelList))
            }
    }

    fun skillEntityToSkillDTO(skillsList: List<Skill>): List<SkillDTO> {
        var skillsDTO = mutableListOf<SkillDTO>()
        for (skill in skillsList) {
            skillsDTO.add(SkillDTO(skill))
        }
        return skillsDTO
    }

    fun stageEntityToStageDTO(stagesList: List<Stage>): List<StageDTO> {
        var stagesDTO = mutableListOf<StageDTO>()
        for (stage in stagesList) {
            stagesDTO.add(StageDTO(stage))
        }
        return stagesDTO
    }

    override fun createCandidate(createCandidateDTO: CreateCandidateDTO): CandidateDTO {
        val candidateDTO = CandidateDTO(createCandidateDTO)

        val candidate = Candidate(candidateDTO)
        candidate.skillList = skillDTOToSkillEntity(createCandidateDTO.skillList)
        candidate.stagelList = stageDTOToStageEntity(createCandidateDTO.stageList, candidate)
        candidate.createdDate = LocalDateTime.now()

        val savedCandidate = candidatesRepository.save(candidate)

        return CandidateDTO(savedCandidate, skillEntityToSkillDTO(candidate.skillList), stageEntityToStageDTO(candidate.stagelList))
    }

    fun skillDTOToSkillEntity(skillsListDTO: List<SkillDTO>): List<Skill> {

        var skillList = mutableListOf<Skill>()
        for (skillDTO in skillsListDTO) {

            val skillsCount = skillsRepository.countByName(skillDTO.name)
            if (skillsCount == 0L) {
                val skillNew = Skill(skillDTO)
                skillNew.createdDate = LocalDateTime.now()
                skillNew.updatedDate = LocalDateTime.now()
                val savedSkill = skillsRepository.save(skillNew)
                skillList.add(savedSkill)
            } else {
                skillList.add(skillsRepository.findByName(skillDTO.name))
            }
        }
        return skillList
    }

    fun stageDTOToStageEntity(stageListDTO: List<StageDTO>, candidate: Candidate): List<Stage> {

        var stageList = mutableListOf<Stage>()

        for (stageDTO in stageListDTO) {

            val stage = Stage(stageDTO, candidate)
            stage.createdDate = LocalDateTime.now()
            stageList.add(stage)
        }
        return stageList


    }
}




