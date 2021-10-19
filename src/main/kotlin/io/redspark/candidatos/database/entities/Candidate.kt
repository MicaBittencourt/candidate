package io.redspark.candidatos.database.entities

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import io.redspark.candidatos.models.dtos.CandidateDTO
import io.redspark.candidatos.models.dtos.CreateCandidateDTO
import io.redspark.candidatos.models.dtos.SkillDTO
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@EntityListeners(AuditingEntityListener::class)
@Table(name = "candidate")
data class Candidate(
    @Id
    @GeneratedValue
    @Column(name = "candidate_id")
    val id: UUID? = null,

    @Column(name = "name")
    var name: String,

    @Column(name = "email")
    var email: String,

    @Column(name = "linkedin")
    var linkedin: String,

    @Column(name = "curriculum")
    var curriculum: String,

    @Column(name = "phone")
    var phone: String,

    @Column(name = "source")
    var source: String,

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "skill_candidate",
        joinColumns = [JoinColumn(name = "candidate_id", referencedColumnName = "candidate_id")],
        inverseJoinColumns = [JoinColumn(name = "skill_id", referencedColumnName = "skill_id")])
    @JsonIgnoreProperties("candidate")
    var skillList: List<Skill> = emptyList(),

    @OneToMany(mappedBy = "candidate", fetch = FetchType.LAZY)
    var stagelList: List<Stage> = emptyList()

){

    @CreatedDate
    @Column(name = "created_date")
    lateinit var createdDate: LocalDateTime

    @LastModifiedDate
    @Column(name = "updated_date")
    lateinit var updatedDate: LocalDateTime

    constructor(candidateDTO: CandidateDTO): this(
        id = candidateDTO.id,
        name = candidateDTO.name,
        email = candidateDTO.email,
        linkedin = candidateDTO.linkedin,
        curriculum = candidateDTO.curriculum,
        phone = candidateDTO.phone,
        source= candidateDTO.source
    )



}

