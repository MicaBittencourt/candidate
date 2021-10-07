package io.redspark.candidatos.models.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import io.redspark.candidatos.database.entities.Candidate
import io.redspark.candidatos.database.entities.Stage
import java.time.LocalDateTime
import java.util.*


data class StageDTO(

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

//    @JsonProperty("id_candidate")
//    val candidate: Candidate,

    @JsonProperty("user_create")
    val user_create: String,

    @JsonProperty("user_update")
    val user_update: String


    ){

    constructor(stage: Stage): this(
//        id = stage.id,
        appointment_date_hour = stage.appointment_date_hour,
        stage_type = stage.stage_type,
        conclusion = stage.conclusion,
        feedback = stage.feedback,
//        candidate = stage.candidate,
        user_create = stage.user_create,
        user_update = stage.user_update,
        schedule_status = stage.schedule_status
    )

    constructor(createCandidateStageDTO: CreateCandidateStageDTO): this(
//        id = createCandidateStageDTO.id,
        appointment_date_hour = createCandidateStageDTO.appointment_date_hour,
        stage_type = createCandidateStageDTO.stage_type,
        schedule_status = createCandidateStageDTO.schedule_status,
        conclusion = createCandidateStageDTO.conclusion,
        feedback = createCandidateStageDTO.feedback,
//        candidate = createCandidateStageDTO.candidate,
        user_create = createCandidateStageDTO.user_create,
        user_update = createCandidateStageDTO.user_update
    )
}

