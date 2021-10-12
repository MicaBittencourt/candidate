package io.redspark.candidatos.modules.customer.controller

import io.redspark.candidatos.models.dtos.CustomerDTO
import io.redspark.candidatos.models.enums.Permissions
import io.redspark.candidatos.modules.customer.service.CustomerService
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
@RequestMapping("/customer")
@Api(value = "Customer", description = "Clientes do grupo redspark")
@Validated
class CustomerController(
    private val customerService: CustomerService
) {

    @GetMapping(produces = ["application/json"])
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Busca todos os clientes cadastrados no sistema", consumes = "application/json")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Retorna uma lista de clientes com paginação", response = CustomerDTO::class),
        ApiResponse(code = 401, message = "unauthorized")
    ])
    fun getCustomerList(): List<CustomerDTO> = customerService.getCustomerList()

    @GetMapping("search", produces = ["application/json"])
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Busca cliente pelo nome", consumes = "application/json")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Retorna pagina de clientes", response = CustomerDTO::class, responseContainer = "Page"),
        ApiResponse(code = 401, message = "unauthorized")
    ])
    fun searchCustomer(
        @RequestParam(value = "term", required = false) term: String?,
        @RequestParam(value = "page", defaultValue = "0") page: Int,
        @RequestParam(value = "size", defaultValue = "10") size: Int,
        @RequestParam(value = "sort", defaultValue = "name") sort: String,
        @RequestParam(value = "direction", defaultValue = "ASC") direction: String
    ): Page<CustomerDTO> = customerService.searchCustomer(term, PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(direction), sort)))

    @GetMapping("{id}", produces = ["application/json"])
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Busca cliente por id", consumes = "application/json")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Retorna o cliente", response = CustomerDTO::class),
        ApiResponse(code = 401, message = "unauthorized"),
        ApiResponse(code = 404, message = "customer.not.found")
    ])
    fun getCustomer(@PathVariable id: Long): CustomerDTO = customerService.getCustomer(id)

    @Secured(Permissions.Constants.ROLE_ADMIN)
    @PostMapping(produces = ["application/json"])
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Cria um novo cliente", consumes = "application/json")
    @ApiResponses(value = [
        ApiResponse(code = 201, message = "Retorna o cliente", response = CustomerDTO::class),
        ApiResponse(code = 400, message = "customer.id.not.empty")
    ])
    fun createCustomer(@Valid @RequestBody customerDTO: CustomerDTO): CustomerDTO = customerService.createCustomer(customerDTO)

    @Secured(Permissions.Constants.ROLE_ADMIN)
    @PutMapping(produces = ["application/json"])
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Altera um cliente existente", consumes = "application/json")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Retorna o usuário", response = CustomerDTO::class),
        ApiResponse(code = 401, message = "unauthorized"),
        ApiResponse(code = 404, message = "customer.not.found")
    ])
    fun updateCustomer(@Valid @RequestBody customerDTO: CustomerDTO): CustomerDTO = customerService.updateCustomer(customerDTO)

}