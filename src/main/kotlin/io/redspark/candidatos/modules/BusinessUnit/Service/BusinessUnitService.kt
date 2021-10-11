package io.redspark.candidatos.modules.BusinessUnit.Service

import io.redspark.candidatos.models.dtos.BusinessUnitDTO

interface BusinessUnitService {

    fun getBuList(): List<BusinessUnitDTO>

}
