package io.redspark.candidatos.models.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import io.redspark.candidatos.database.entities.Skill
import java.util.*

data class SkillDTO (

    @JsonProperty("id")
    val id: Long? = null,

    @JsonProperty("name")
    var name: String

){
    constructor(skill: Skill): this(
        id = skill.id,
        name = skill.name
    )



}
