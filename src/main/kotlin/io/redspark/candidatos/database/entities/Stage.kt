package io.redspark.candidatos.database.entities

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@EntityListeners(AuditingEntityListener::class)
@Table(name = "TB_STAGE")
data class Stage(

    @Id
    @GeneratedValue
    @Column(name = "stage_id")
    val id: UUID? = null,

    @Column(name = "appointment_date_hour")
    val appointment_date_hour: Date,

    @Column(name = "stage_type")
    val stage_type: String,

    @Column(name = "schedule_status")
    val schedule_status: String,

    @Column(name = "conclusion")
    val conclusion: String,

    @Column(name = "feedback")
    val feedback: String,

    @OneToOne(targetEntity = Candidate::class, fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate")
    val candidate: Candidate,

    @Column(name = "user_create")
    val user_create: String,

    @Column(name = "user_update")
    val user_update: String


    ){
/*constructor(stage: stageDTO) : this(
    id = stage.id,
    appointment_date_hour = stage.appointment_date_hour,
    stage_type = stage.stage_type,
    schedule_status = stage.schedule_status,
    conclusion = stage.conclusion,
    feedback = stage.feedback,
    candidate_id = stage.candidate_id,
    user_create = stage.user_create,
    user_update = stage.user_update

    )*/

    @CreatedDate
    @Column(name = "created_date")
    lateinit var createdDate: LocalDateTime

    @LastModifiedDate
    @Column(name = "updated_date")
    lateinit var updatedDate: LocalDateTime
}