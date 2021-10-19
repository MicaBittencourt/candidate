package io.redspark.candidatos.modules.skill.controller

import io.redspark.candidatos.models.dtos.SkillDTO
import io.redspark.candidatos.models.enums.Permissions
import io.redspark.candidatos.modules.skill.service.SkillService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.security.access.annotation.Secured
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/skill")
@Api(value = "skill")
@Validated
class SkillController (
        private val skillService: SkillService
) {

        @GetMapping(produces = ["application/json"])
        @ResponseStatus(HttpStatus.OK)
        @ApiOperation(value = "Busca todos skill cadastrados no sistema", consumes = "application/json")
        @ApiResponses(value = [
                ApiResponse(code = 200, message = "Retorna uma skill cadastradas", response = SkillDTO::class),
                ApiResponse(code = 401, message = "unauthorized")
        ])
        fun getSkillList(): List<SkillDTO> = skillService.getSkillList()

        @Secured(Permissions.Constants.ROLE_ADMIN)
        @PostMapping(produces = ["application/json"])
        @ResponseStatus(HttpStatus.CREATED)
        @ApiOperation(value = "Cria uma skill", consumes = "application/json")
        @ApiResponses(value = [
                ApiResponse(code = 201, message = "Retorna skill", response = SkillDTO::class),
                ApiResponse(code = 400, message = "skill.id.not.empty")
        ])
        fun createSkill(@Valid @RequestBody skillDTO: SkillDTO): SkillDTO = skillService.createSkill(skillDTO)

        @Secured(Permissions.Constants.ROLE_ADMIN)
        @PutMapping(produces = ["application/json"])
        @ResponseStatus(HttpStatus.OK)
        @ApiOperation(value = "Atualizar uma nova uma skill", consumes = "application/json")
        @ApiResponses(value = [
                ApiResponse(code = 201, message = "Retorna skill cadastrada", response = SkillDTO::class),
                ApiResponse(code = 400, message = "unauthorized"),
                ApiResponse(code = 404, message = "skill.not.found")
        ])

        fun updateSkill(@Valid @RequestBody skillDTO: SkillDTO): SkillDTO = skillService.updateSkill(skillDTO)

        @GetMapping("{id}", produces = ["application/json"])
        @ResponseStatus(HttpStatus.OK)
        @ApiOperation(value = "Busca skill por id", consumes = "application/json")
        @ApiResponses(value = [
                ApiResponse(code = 200, message = "Retorna skill", response = SkillDTO::class),
                ApiResponse(code = 401, message = "unauthorized"),
                ApiResponse(code = 404, message = "skill.not.found")
        ])
        fun getSkill(@PathVariable id: Long): SkillDTO = skillService.getSkill(id)
}