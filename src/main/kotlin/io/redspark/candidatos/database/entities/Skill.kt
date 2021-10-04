package io.redspark.candidatos.database.entities

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@EntityListeners(AuditingEntityListener::class)
@Table(name = "skill")
data class Skill(
    @Id
    @GeneratedValue
    @Column(name = "skill_id")
    val id: UUID? = null,

    @Column(name = "name")
    val name: String,

    @ManyToMany(mappedBy = "skillList")
    val candidateList: List<Candidate> = mutableListOf()

){
/*constructor(skill: skillDTO) : this(
    id = skill.id,
    name = skill.name

    )*/

    @CreatedDate
    @Column(name = "created_date")
    lateinit var createdDate: LocalDateTime

    @LastModifiedDate
    @Column(name = "updated_date")
    lateinit var updatedDate: LocalDateTime

}