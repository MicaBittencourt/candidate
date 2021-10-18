package io.redspark.candidatos.database.entities

import io.redspark.candidatos.models.dtos.SkillDTO
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@EntityListeners(AuditingEntityListener::class)
@Table(name = "skill")
data class Skill (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "skill_id")
        val id: Long? = null,

        @Column(name = "name")
        var name: String

        ){

    @CreatedDate
    @Column(name = "created_date", updatable = false)
    lateinit var createdDate: LocalDateTime

    @LastModifiedDate
    @Column(name = "updated_date")
    lateinit var updatedDate: LocalDateTime

    constructor(skillDTO: SkillDTO): this(
            id = skillDTO.id,
            name = skillDTO.name
    )

}