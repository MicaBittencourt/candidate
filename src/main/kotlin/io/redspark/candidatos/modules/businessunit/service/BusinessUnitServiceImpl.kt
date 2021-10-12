package io.redspark.candidatos.modules.businessunit.service

import io.redspark.candidatos.database.repositories.BusinessUnitRepository
import io.redspark.candidatos.models.dtos.BusinessUnitDTO
import org.springframework.stereotype.Service

@Service
class BusinessUnitServiceImpl(
    private val businessUnitRepository: BusinessUnitRepository
) : BusinessUnitService {

    override fun getBuList(): List<BusinessUnitDTO> = businessUnitRepository.findAll()
        .map { BusinessUnitDTO(it) }

}