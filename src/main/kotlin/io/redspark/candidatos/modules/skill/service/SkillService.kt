package io.redspark.candidatos.modules.skill.service

import io.redspark.candidatos.models.dtos.SkillDTO

interface SkillService {

    fun updateSkill(skillDTO: SkillDTO): SkillDTO
    fun getSkillList(): List<SkillDTO>
    fun createSkill(skillDTO: SkillDTO): SkillDTO
    fun getSkill(id: Long): SkillDTO

}
