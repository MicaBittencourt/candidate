package io.redspark.candidatos.models.errors

import org.springframework.web.server.ResponseStatusException

class ServiceException(
    serviceError: ServiceError
) : ResponseStatusException(serviceError.status, serviceError.message)