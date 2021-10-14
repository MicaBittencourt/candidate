package io.redspark.candidatos.database.entities

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import io.redspark.candidatos.models.dtos.SkillDTO
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

//TODO - alterar tipo do ID para Long
@Entity
@EntityListeners(AuditingEntityListener::class)
@Table(name = "skill")
data class Skill(
    @Id
    @GeneratedValue
    @Column(name = "skill_id")
    val id: Long? = null,

    @Column(name = "name")
    val name: String,

    @ManyToMany(mappedBy = "skillList")
    @JsonIgnoreProperties("skillList")
    val candidateList: List<Candidate> = mutableListOf()


){
    constructor(skillDTO: SkillDTO) : this(
        id = skillDTO.id,
        name = skillDTO.name,
    )

    @CreatedDate
    @Column(name = "created_date")
    lateinit var createdDate: LocalDateTime

    @LastModifiedDate
    @Column(name = "updated_date")
    lateinit var updatedDate: LocalDateTime

}