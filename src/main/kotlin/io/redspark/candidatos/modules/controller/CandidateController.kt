package io.redspark.candidatos.modules.controller

import io.redspark.candidatos.models.dtos.CandidateDTO
import io.redspark.candidatos.models.dtos.CreateCandidateDTO
import io.redspark.candidatos.models.dtos.CreateCandidateStageDTO
import io.redspark.candidatos.models.dtos.StageDTO
import io.redspark.candidatos.modules.service.CandidateService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/candidates")
@Api(value = "Candidate", description = "Controle para cadastro de candidatos")
class CandidateController(
    private val candidateService: CandidateService

) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Busca todos candidatos cadastrados", consumes = "application/json")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Retorna todos candidatos cadastrados", response = CandidateDTO::class),
        ApiResponse(code = 401, message = "unauthorized")
    ])
    fun getCandidateList(): List<CandidateDTO> = candidateService.getCandidateList()

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Cadastra candidato", consumes = "application/json")
    @ApiResponses(value = [
        ApiResponse(code = 201, message = "Retorna skill cadastrada", response = CandidateDTO::class),
        ApiResponse(code = 401, message = "unauthorized")
    ])
    fun createCandidate(@RequestBody @Valid createCandidateDTO: CreateCandidateDTO): CandidateDTO = candidateService.createCandidate(createCandidateDTO)






}