package io.redspark.candidatos.modules.skill.controller

import io.redspark.candidatos.models.dtos.CreateSkillDTO
import io.redspark.candidatos.models.dtos.SkillDTO
import io.redspark.candidatos.modules.skill.service.SkillService
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/skill")
class SkillController(
    private val skillService: SkillService
) {

    @GetMapping
    fun getSkillsList(): List<SkillDTO> = skillService.getSkillsList()

    @PostMapping
    fun createSkill(@RequestBody @Valid createSkillDTO: CreateSkillDTO): SkillDTO = skillService.createSkill(createSkillDTO)

}