package io.redspark.candidatos.modules.customer.service

import io.redspark.candidatos.config.logger.LoggerDelegate
import io.redspark.candidatos.config.logger.logCreated
import io.redspark.candidatos.config.logger.logUpdated
import io.redspark.candidatos.database.entities.Customer
import io.redspark.candidatos.database.repositories.CustomerRepository
import io.redspark.candidatos.models.dtos.CustomerDTO
import io.redspark.candidatos.models.errors.ServiceError
import io.redspark.candidatos.models.errors.ServiceException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CustomerServiceImpl(
    private val customerRepository: CustomerRepository
) : CustomerService {

    private val logger by LoggerDelegate()

    override fun getCustomerList(): List<CustomerDTO> = customerRepository.findAll()
        .map { CustomerDTO(it) }

    override fun searchCustomer(term: String?, pageable: Pageable): Page<CustomerDTO> {
        val page = if (term == null) {
            customerRepository.findAll(pageable)
        } else {
            customerRepository.findAllByNameContainingIgnoreCase(term, pageable)
        }

        return page.map { CustomerDTO(it) }
    }

    override fun getCustomer(id: Long): CustomerDTO {
        val customer = customerRepository.findByIdOrNull(id) ?: throw ServiceException(ServiceError.CUSTOMER_NOT_FOUND)
        return CustomerDTO(customer)
    }

    override fun createCustomer(customerDTO: CustomerDTO): CustomerDTO {
        if (customerDTO.id != null) {
            throw ServiceException(ServiceError.CUSTOMER_ID_NOT_EMPTY)
        }

        var customer = Customer(customerDTO)
        customer = customerRepository.save(customer)

        logger.logCreated(customer)

        return CustomerDTO(customer)
    }

    override fun updateCustomer(customerDTO: CustomerDTO): CustomerDTO {
        if (customerDTO.id == null || customerRepository.existsById(customerDTO.id).not()) {
            throw ServiceException(ServiceError.CUSTOMER_NOT_FOUND)
        }

        var customer = Customer(customerDTO)
        customer = customerRepository.save(customer)

        logger.logUpdated(customer)

        return CustomerDTO(customer)
    }

}