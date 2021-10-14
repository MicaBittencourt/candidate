package io.redspark.candidatos.models.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import io.redspark.candidatos.database.entities.Candidate
import java.time.LocalDateTime
import java.util.*

data class CreateCandidateStageDTO (

    @JsonProperty("id")
    val id: Long? = null,

    @JsonProperty("appointment_date_hour")
    val appointment_date_hour: LocalDateTime,

    @JsonProperty("stage_type")
    val stage_type: String,

    @JsonProperty("schedule_status")
    val schedule_status: String,

    @JsonProperty("conclusion")
    val conclusion: Boolean,

    @JsonProperty("feedback")
    val feedback: String,

    @JsonProperty("id_candidate")
    val candidate: Candidate,

    @JsonProperty("user_create")
    val user_create: String,

    @JsonProperty("user_update")
    val user_update: String

    )
