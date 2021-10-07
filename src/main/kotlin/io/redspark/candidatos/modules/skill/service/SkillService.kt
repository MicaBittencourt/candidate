package io.redspark.candidatos.modules.skill.service

import io.redspark.candidatos.models.dtos.CandidateDTO
import io.redspark.candidatos.models.dtos.CreateCandidateDTO
import io.redspark.candidatos.models.dtos.CreateSkillDTO
import io.redspark.candidatos.models.dtos.SkillDTO

interface SkillService {
    abstract fun getSkillsList(): List<SkillDTO>
    abstract fun createSkill(createSkillDTO: CreateSkillDTO): SkillDTO



}