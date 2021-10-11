package io.redspark.candidatos.database.entities

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@EntityListeners(AuditingEntityListener::class)
@Table(name = "proj_user")
class User(
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    val id: UUID? = null,

    @Column(name = "name")
    val name: String,

    @Column(name = "email")
    val email: String
) {

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    var permissionList: List<UserPermission> = emptyList()

    @CreatedDate
    @Column(name = "created_date")
    lateinit var createdDate: LocalDateTime

    @LastModifiedDate
    @Column(name = "updated_date")
    lateinit var updatedDate: LocalDateTime

}