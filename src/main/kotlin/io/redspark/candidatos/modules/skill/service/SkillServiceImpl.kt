package io.redspark.candidatos.modules.skill.service

import io.redspark.candidatos.database.entities.Skill
import io.redspark.candidatos.database.repositories.SkillRepository
import io.redspark.candidatos.models.dtos.CreateSkillDTO
import io.redspark.candidatos.models.dtos.SkillDTO
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class SkillServiceImpl(
    private var skillsRepository: SkillRepository
) : SkillService {
    override fun getSkillsList(): List<SkillDTO> {
        return skillsRepository.findAll()
            .map { SkillDTO(it)}
    }

    override fun createSkill(createSkillDTO: CreateSkillDTO): SkillDTO {
        val skillDTO = SkillDTO(createSkillDTO)
        val skill = Skill(skillDTO)
        skill.createdDate = LocalDateTime.now()
        val savedSkill = skillsRepository.save(skill)
        return SkillDTO(savedSkill)
    }
}