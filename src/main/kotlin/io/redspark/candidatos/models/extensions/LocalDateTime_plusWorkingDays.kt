package io.redspark.candidatos.models.extensions

import java.time.DayOfWeek
import java.time.LocalDateTime

    fun LocalDateTime.plusWorkingDays(workingDays: Int): LocalDateTime {
        var daysToAdd = 0L
        var workingDaysCount = 0L

        while(workingDaysCount < workingDays) {
            daysToAdd++
            if(DayOfWeek.from(this.plusDays(daysToAdd)) !in listOf(DayOfWeek.SUNDAY, DayOfWeek.SATURDAY)) {
                workingDaysCount++
            }

        }

        return this.plusDays(daysToAdd)
    }