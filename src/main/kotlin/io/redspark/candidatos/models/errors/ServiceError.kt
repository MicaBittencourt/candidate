package io.redspark.candidatos.models.errors

import org.springframework.http.HttpStatus

enum class ServiceError(val status: HttpStatus, val message: String) {
    BUSINESS_UNIT_NOT_FOUND(HttpStatus.NOT_FOUND, "business.unit.not.found"),
    CUSTOMER_NOT_FOUND(HttpStatus.NOT_FOUND, "customer.not.found"),
    CUSTOMER_ID_NOT_EMPTY(HttpStatus.BAD_REQUEST, "customer.id.not.empty"),
    JOB_NOT_FOUND(HttpStatus.NOT_FOUND, "job.not.found"),
    JOB_TITLE_NOT_FOUND(HttpStatus.NOT_FOUND, "job.title.not.found"),
    JOB_TITLE_ID_NOT_EMPTY(HttpStatus.BAD_REQUEST, "job.title.id.not.empty"),
    CANDIDATE_NOT_FOUND(HttpStatus.NOT_FOUND, "candidate.not.found");
}
