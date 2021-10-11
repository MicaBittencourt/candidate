package io.redspark.candidatos.modules.BusinessUnit.Controller

import io.redspark.candidatos.models.dtos.BusinessUnitDTO
import io.redspark.candidatos.modules.BusinessUnit.Service.BusinessUnitService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/business-unit")
@Api(value = "Business Unit", description = "Unidades de negócio do grupo redspark")
@Validated
class BusinessUnitController(
    private val businessUnitService: BusinessUnitService
) {

    @GetMapping(produces = ["application/json"])
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Busca todos os usuários cadastrados no sistema", consumes = "application/json")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Retorna uma lista de unidades de negócio do grupo redspark", response = BusinessUnitDTO::class, responseContainer = "List"),
        ApiResponse(code = 401, message = "unauthorized")
    ])
    fun getBuList(): List<BusinessUnitDTO> = businessUnitService.getBuList()


}