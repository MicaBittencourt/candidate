package io.redspark.candidatos.database.entities

import io.redspark.candidatos.models.dtos.JobTitleDTO
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@EntityListeners(AuditingEntityListener::class)
@Table(name = "job_title")
class JobTitle(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_title_id")
    val id: Long? = null,

    @Column(name = "name")
    val name: String
) {

    @CreatedDate
    @Column(name = "created_date", updatable = false)
    lateinit var createdDate: LocalDateTime

    @LastModifiedDate
    @Column(name = "updated_date")
    lateinit var updatedDate: LocalDateTime

    @OneToMany(mappedBy = "jobTitle", fetch = FetchType.LAZY)
    var jobList: List<Job> = emptyList()

    constructor(jobTitleDTO: JobTitleDTO) : this(
        id = jobTitleDTO.id,
        name = jobTitleDTO.name
    )

}