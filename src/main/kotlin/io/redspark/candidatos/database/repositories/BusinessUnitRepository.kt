package io.redspark.candidatos.database.repositories

import io.redspark.candidatos.database.entities.BusinessUnit
import io.redspark.candidatos.database.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface BusinessUnitRepository : JpaRepository<BusinessUnit, Long>