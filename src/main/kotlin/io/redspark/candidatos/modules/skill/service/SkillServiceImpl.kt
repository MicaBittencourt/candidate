package io.redspark.candidatos.modules.skill.service

import io.redspark.candidatos.config.logger.LoggerDelegate
import io.redspark.candidatos.config.logger.logCreated
import io.redspark.candidatos.config.logger.logUpdated
import io.redspark.candidatos.database.entities.Skill
import io.redspark.candidatos.database.repositories.SkillRepository
import io.redspark.candidatos.models.dtos.SkillDTO
import io.redspark.candidatos.models.errors.ServiceError
import io.redspark.candidatos.models.errors.ServiceException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class SkillServiceImpl(

        private val skillRepository: SkillRepository
) : SkillService {

    private val logger by LoggerDelegate()

    override fun createSkill(skillDTO: SkillDTO): SkillDTO {
        if (skillDTO.id != null) {
            throw ServiceException(ServiceError.SKILL_ID_NOT_EMPTY)
        }

        var skill = Skill(skillDTO)
        skill = skillRepository.save(skill)

        logger.logCreated(skill)

        return SkillDTO(skill)
    }

    override fun getSkill(id: Long): SkillDTO  {
        val skill = skillRepository.findByIdOrNull(id) ?: throw ServiceException(ServiceError.SKILL_ID_NOT_EMPTY)
        return SkillDTO(skill)
    }

    override fun updateSkill(skillDTO: SkillDTO): SkillDTO {
            if (skillDTO.id == null || skillRepository.existsById(skillDTO.id).not()) {
                throw ServiceException(ServiceError.SKILL_ID_NOT_EMPTY)
            }

            var skill = Skill(skillDTO)
            skill = skillRepository.save(skill)

            logger.logUpdated(skill)

            return SkillDTO(skill)

    }


    override fun getSkillList(): List<SkillDTO> = skillRepository.findAll()
            .map { SkillDTO(it) }


}