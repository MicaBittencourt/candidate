package io.redspark.candidatos.database.entities

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@EntityListeners(AuditingEntityListener::class)
@Table(name = "business_unit")
class BusinessUnit(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bu_id")
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

    @OneToMany(mappedBy = "businessUnit", fetch = FetchType.LAZY)
    var jobList: List<Job> = emptyList()

}