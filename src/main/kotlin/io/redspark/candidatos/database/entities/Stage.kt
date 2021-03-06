package io.redspark.candidatos.database.entities

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import io.redspark.candidatos.models.enums.StageScheduleStatus
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@EntityListeners(AuditingEntityListener::class)
@Table(name = "stage")
data class Stage(
    @Id
    @GeneratedValue
    @Column(name = "stage_id")
    val id: Long? = null,

    @Column(name = "appointment_date_hour")
    val appointment_date_hour: LocalDateTime,

    @Column(name = "stage_type")
    val stage_type: String,

    @Column(name = "schedule_status")
    @Enumerated(EnumType.STRING)
    val schedule_status: StageScheduleStatus,

    @Column(name = "conclusion")
    val conclusion: Boolean,

    @Column(name = "feedback")
    val feedback: String,

    @ManyToOne(targetEntity = Candidate::class, fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_id")
    @JsonIgnoreProperties("stageList")
    val candidate: Candidate,

    @Column(name = "user_create")
    val user_create: String,

    @Column(name = "user_update")
    val user_update: String

){
    @CreatedDate
    @Column(name = "created_date")
    lateinit var createdDate: LocalDateTime

    @LastModifiedDate
    @Column(name = "updated_date")
    lateinit var updatedDate: LocalDateTime

}