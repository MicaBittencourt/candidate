package io.redspark.candidatos.modules.customer.service

import io.redspark.candidatos.models.dtos.CustomerDTO
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface CustomerService {

    fun getCustomerList(): List<CustomerDTO>
    fun searchCustomer(term: String?, pageable: Pageable): Page<CustomerDTO>
    fun getCustomer(id: Long): CustomerDTO
    fun createCustomer(customerDTO: CustomerDTO): CustomerDTO
    fun updateCustomer(customerDTO: CustomerDTO): CustomerDTO

}