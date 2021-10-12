package io.redspark.candidatos.modules.businessunit.service

import io.redspark.candidatos.models.dtos.BusinessUnitDTO

interface BusinessUnitService {

    fun getBuList(): List<BusinessUnitDTO>

}
