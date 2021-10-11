package io.redspark.candidatos.database.entities

import io.redspark.candidatos.models.enums.Permissions
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.io.Serializable
import javax.persistence.*

@Entity
@EntityListeners(AuditingEntityListener::class)
@Table(name = "user_permission")
class UserPermission(
    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    val user: User,

    @Id
    @Column(name = "permission")
    @Enumerated(EnumType.STRING)
    val permission: Permissions,
 ) : Serializable