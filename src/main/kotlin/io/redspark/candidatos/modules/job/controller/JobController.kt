package io.redspark.candidatos.modules.job.controller

import io.redspark.candidatos.models.dtos.JobCreateDTO
import io.redspark.candidatos.models.dtos.JobDTO
import io.redspark.candidatos.models.dtos.JobPageDTO
import io.redspark.candidatos.models.enums.JobStatus
import io.redspark.candidatos.models.enums.Permissions
import io.redspark.candidatos.modules.job.service.JobService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.security.access.annotation.Secured
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/job")
@Api(value = "Job ", description = "Vagas")
@Validated
class JobController(
    private val jobService: JobService
) {

    @GetMapping(produces = ["application/json"])
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Busca as vagas abertas", consumes = "application/json")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Retorna pagina de vagas", response = JobPageDTO::class, responseContainer = "Page"),
        ApiResponse(code = 401, message = "unauthorized")
    ])
    fun getJobPage(
        @RequestParam(value = "page", defaultValue = "0") page: Int,
        @RequestParam(value = "size", defaultValue = "10") size: Int,
        @RequestParam(value = "sort", defaultValue = "createdDate") sort: String,
        @RequestParam(value = "direction", defaultValue = "ASC") direction: String
    ): Page<JobPageDTO> = jobService.getJobPage(PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(direction), sort)))

    @GetMapping("{id}", produces = ["application/json"])
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Busca a vaga por id", consumes = "application/json")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Retorna a vaga de ocupação", response = JobDTO::class),
        ApiResponse(code = 401, message = "unauthorized"),
        ApiResponse(code = 404, message = "job.not.found")
    ])
    fun getJob(@PathVariable id: Long): JobDTO = jobService.getJob(id)

    @Secured(Permissions.Constants.ROLE_ADMIN, Permissions.Constants.ROLE_PEOPLE)
    @PostMapping(produces = ["application/json"])
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Cria uma nova área de ocupação", consumes = "application/json")
    @ApiResponses(value = [
        ApiResponse(code = 201, message = "Retorna a área de ocupação", response = JobDTO::class),
        ApiResponse(code = 401, message = "unauthorized"),
        ApiResponse(code = 404, message = "customer.not.found | job.title.not.found | business.unit.not.found")
    ])
    fun createJob(@Valid @RequestBody jobCreateDTO: JobCreateDTO): JobDTO = jobService.createJob(jobCreateDTO)

    @Secured(Permissions.Constants.ROLE_ADMIN, Permissions.Constants.ROLE_PEOPLE)
    @PostMapping("{id}/close")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Fecha a vaga", consumes = "application/json")
    @ApiResponses(value = [
        ApiResponse(code = 204, message = "Finaliza a vaga"),
        ApiResponse(code = 401, message = "unauthorized"),
        ApiResponse(code = 404, message = "job.not.found")
    ])
    fun closeJob(@PathVariable id: Long) = jobService.closeJob(id)

    @Secured(Permissions.Constants.ROLE_USER)
    @GetMapping("status", produces = ["application/json"])
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Busca vaga por filtro Status", consumes = "application/json")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Retorna pagina de vagas por status", response = JobDTO::class, responseContainer = "Page"),
        ApiResponse(code = 401, message = "unauthorized")
    ])
    fun searchJob(
        @RequestParam(value = "status", required = false) status: JobStatus?,
        @RequestParam(value = "term", required = false) term: String?,
        @RequestParam(value = "page", defaultValue = "0") page: Int,
        @RequestParam(value = "size", defaultValue = "10") size: Int,
        @RequestParam(value = "sort", defaultValue = "createdDate") sort: String,
        @RequestParam(value = "direction", defaultValue = "DESC") direction: String
    ): Page<JobDTO> = jobService.searchJob(term, status, PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(direction), sort)))


}