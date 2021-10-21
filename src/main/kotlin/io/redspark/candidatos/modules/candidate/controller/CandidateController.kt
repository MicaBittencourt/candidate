package io.redspark.candidatos.modules.candidate.controller

import io.redspark.candidatos.models.dtos.*
import io.redspark.candidatos.models.enums.Permissions
import io.redspark.candidatos.modules.candidate.service.CandidateService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.security.access.annotation.Secured
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@Validated
@RestController
@RequestMapping("/candidates")
@Api(value = "Candidate", description = "Controle para cadastro de candidatos")
class CandidateController(
    private val candidateService: CandidateService

) {

    @Secured(Permissions.Constants.ROLE_USER)
    @GetMapping(produces = ["application/json"])
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Busca todos candidatos cadastrados", consumes = "application/json")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Retorna todos candidatos cadastrados", response = CandidateDTO::class),
        ApiResponse(code = 401, message = "unauthorized")
    ])
    fun getCandidateList(@RequestHeader(HttpHeaders.AUTHORIZATION) email: String): List<CandidateDTO> = candidateService.getCandidateList()

    @Secured(Permissions.Constants.ROLE_ADMIN)
    @PostMapping(produces = ["application/json"])
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Cadastra candidato", consumes = "application/json")
    @ApiResponses(value = [
        ApiResponse(code = 201, message = "Retorna skill cadastrada", response = CreateCandidateDTO::class),
        ApiResponse(code = 400, message = "content.length.field.required"),
        ApiResponse(code = 401, message = "unauthorized")
    ])
    fun createCandidate(@RequestBody @Valid createCandidateDTO: CreateCandidateDTO): CandidateDTO = candidateService.createCandidate(createCandidateDTO)

    @Secured(Permissions.Constants.ROLE_ADMIN)
    @PutMapping( produces = ["application/json"])
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Altera candidato existente", consumes = "application/json")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Retorna candidato",response = UpdateCandidateDTO::class),
        ApiResponse(code = 401, message = "unauthorized"),
        ApiResponse(code = 404, message = "candidate.not.found")
    ])
    fun updateCandidate(@Valid @RequestBody updateCandidateDTO: UpdateCandidateDTO): CandidateDTO = candidateService.updateCandidate(updateCandidateDTO)

    @Secured(Permissions.Constants.ROLE_USER)
    @GetMapping("{id}", produces = ["application/json"])
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Busca candidatos por id", consumes = "application/json")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Retorna a área de ocupação", response = CandidateDTO::class),
        ApiResponse(code = 401, message = "unauthorized"),
        ApiResponse(code = 404, message = "candidate.not.found")
    ])
    fun getCandidate(@PathVariable id: UUID): CandidateDTO = candidateService.getCandidate(id)







}