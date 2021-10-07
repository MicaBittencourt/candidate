package io.redspark.candidatos.modules.skill.controller

import io.redspark.candidatos.models.dtos.CandidateDTO
import io.redspark.candidatos.models.dtos.CreateSkillDTO
import io.redspark.candidatos.models.dtos.SkillDTO
import io.redspark.candidatos.modules.skill.service.SkillService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/skill")
@Api(value = "Skill", description = "Controle para cadastro de skills")
class SkillController(
    private val skillService: SkillService
) {

    @GetMapping
    @ApiOperation(value = "Busca skill cadastradas", consumes = "application/json")
    @ApiResponses(value = [
        ApiResponse(code = 201, message = "Retorna todas skills cadastradas", response = CandidateDTO::class),
        ApiResponse(code = 401, message = "unauthorized")
    ])
    fun getSkillsList(): List<SkillDTO> = skillService.getSkillsList()

    @PostMapping
    fun createSkill(@RequestBody @Valid createSkillDTO: CreateSkillDTO): SkillDTO = skillService.createSkill(createSkillDTO)

}