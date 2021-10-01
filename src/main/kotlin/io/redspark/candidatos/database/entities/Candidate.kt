package io.redspark.candidatos.database.entities

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

    @ManyToMany
    @JoinTable(
        name = "skill_candidate",
        joinColumns = [JoinColumn(name = "candidate_id")],
        inverseJoinColumns = [JoinColumn(name = "skill_id")])
    val skillList: List<Skill> = emptyList(),

    ){
/*constructor(candidate: candidateDTO) : this(
    id = candidate.id,
    name = candidate.name,
    email = candidate.email,
    linkedin = candidate.linkedin,
    cv = candidate.cv,
    phone = candidate.phone,
    source = candidate.source

    )*/

    @CreatedDate
    @Column(name = "created_date")
    lateinit var createdDate: LocalDateTime

    @LastModifiedDate
    @Column(name = "updated_date")
    lateinit var updatedDate: LocalDateTime

}

