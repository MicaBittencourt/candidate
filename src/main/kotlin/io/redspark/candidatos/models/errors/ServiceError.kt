package io.redspark.candidatos.models.errors

import org.springframework.http.HttpStatus

enum class ServiceError(val status: HttpStatus, val message: String) {
    CUSTOMER_NOT_FOUND(HttpStatus.NOT_FOUND, "customer.not.found"),
    CUSTOMER_ID_NOT_EMPTY(HttpStatus.BAD_REQUEST, "customer.id.not.empty");
}
