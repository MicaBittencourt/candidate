package io.redspark.candidatos.models.extensions

import java.time.DayOfWeek
import java.time.LocalDateTime

    fun LocalDateTime.workingDaysFromDate(date: LocalDateTime): Int {
        val dateRange = generateSequence(date) { d -> d.plusDays(1).takeIf { it <= this }}
        val total = dateRange.count {
        DayOfWeek.from(it) !in listOf(DayOfWeek.SUNDAY, DayOfWeek.SATURDAY)
    }
        return total
}