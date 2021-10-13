package io.redspark.candidatos.database.entities

import io.redspark.candidatos.models.dtos.CustomerDTO
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@EntityListeners(AuditingEntityListener::class)
@Table(name = "customer")
class Customer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
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

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    var jobList: List<Job> = emptyList()

    constructor(customerDTO: CustomerDTO) : this(
        id = customerDTO.id,
        name = customerDTO.name
    )

}


