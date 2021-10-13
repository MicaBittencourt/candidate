package io.redspark.candidatos.modules.jobtitle.controller

import io.redspark.candidatos.models.dtos.JobTitleDTO
import io.redspark.candidatos.models.enums.Permissions
import io.redspark.candidatos.modules.jobtitle.service.JobTitleService
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
@RequestMapping("/job-title")
@Api(value = "Job Title", description = "Área de ocupação")
@Validated
class JobTitleController(
    private val jobTitleService: JobTitleService
) {

    @GetMapping(produces = ["application/json"])
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Busca todos as área de ocupação cadastradas no sistema", consumes = "application/json")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Retorna uma lista de áreas de ocupação com paginação", response = JobTitleDTO::class),
        ApiResponse(code = 401, message = "unauthorized")
    ])
    fun getJobTitleList(): List<JobTitleDTO> = jobTitleService.getJobTitleList()

    @GetMapping("search", produces = ["application/json"])
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Busca área de ocupação pelo nome", consumes = "application/json")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Retorna pagina de área de ocupação", response = JobTitleDTO::class, responseContainer = "Page"),
        ApiResponse(code = 401, message = "unauthorized")
    ])
    fun searchCustomer(
        @RequestParam(value = "term", required = false) term: String?,
        @RequestParam(value = "page", defaultValue = "0") page: Int,
        @RequestParam(value = "size", defaultValue = "10") size: Int,
        @RequestParam(value = "sort", defaultValue = "name") sort: String,
        @RequestParam(value = "direction", defaultValue = "ASC") direction: String
    ): Page<JobTitleDTO> = jobTitleService.searchJobTitle(term, PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(direction), sort)))

    @GetMapping("{id}", produces = ["application/json"])
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Busca área de ocupação por id", consumes = "application/json")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Retorna a área de ocupação", response = JobTitleDTO::class),
        ApiResponse(code = 401, message = "unauthorized"),
        ApiResponse(code = 404, message = "job.title.not.found")
    ])
    fun getJobTitle(@PathVariable id: Long): JobTitleDTO = jobTitleService.getJobTitle(id)

    @Secured(Permissions.Constants.ROLE_ADMIN)
    @PostMapping(produces = ["application/json"])
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Cria uma nova área de ocupação", consumes = "application/json")
    @ApiResponses(value = [
        ApiResponse(code = 201, message = "Retorna a área de ocupação", response = JobTitleDTO::class),
        ApiResponse(code = 400, message = "job.title.id.not.empty")
    ])
    fun createJobTitle(@Valid @RequestBody jobTitleDTO: JobTitleDTO): JobTitleDTO = jobTitleService.createJobTitle(jobTitleDTO)

    @Secured(Permissions.Constants.ROLE_ADMIN)
    @PutMapping(produces = ["application/json"])
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Altera um cliente existente", consumes = "application/json")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Retorna a área de ocupação", response = JobTitleDTO::class),
        ApiResponse(code = 401, message = "unauthorized"),
        ApiResponse(code = 404, message = "job.title.not.found")
    ])
    fun updateJobTitle(@Valid @RequestBody jobTitleDTO: JobTitleDTO): JobTitleDTO = jobTitleService.updateJobTitle(jobTitleDTO)

}