package io.redspark.candidatos.database.entities

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import io.redspark.candidatos.models.dtos.CandidateDTO
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

//TODO - alterar mutablelist para emptyList
@Entity
@EntityListeners(AuditingEntityListener::class)
@Table(name = "candidate")
data class Candidate(
    @Id
    @GeneratedValue
    @Column(name = "candidate_id")
    val id: UUID? = null,

    @Column(name = "name")
    val name: String,

    @Column(name = "email")
    val email: String,

    @Column(name = "linkedin")
    val linkedin: String,

    @Column(name = "curriculum")
    val curriculum: String,

    @Column(name = "phone")
    val phone: String,

    @Column(name = "source")
    val source: String,

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "skill_candidate",
        joinColumns = [JoinColumn(name = "candidate_id", referencedColumnName = "candidate_id")],
        inverseJoinColumns = [JoinColumn(name = "skill_id", referencedColumnName = "skill_id")])
    @JsonIgnoreProperties("candidate")
    var skillList: List<Skill> = emptyList(),

    @OneToMany(mappedBy = "candidate", fetch = FetchType.LAZY)
    var stagelList: List<Stage> = emptyList()



){
    constructor(candidateDTO: CandidateDTO): this(
        id = candidateDTO.id,
        name = candidateDTO.name,
        email = candidateDTO.email,
        linkedin = candidateDTO.linkedin,
        curriculum = candidateDTO.curriculum,
        phone = candidateDTO.phone,
        source= candidateDTO.source
    )

    @CreatedDate
    @Column(name = "created_date")
    lateinit var createdDate: LocalDateTime

    @LastModifiedDate
    @Column(name = "updated_date")
    lateinit var updatedDate: LocalDateTime

}

