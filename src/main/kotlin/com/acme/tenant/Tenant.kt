package com.acme.tenant

import com.fasterxml.jackson.annotation.JsonIgnore

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import org.hibernate.validator.constraints.Length
import java.util.*

@Entity
@Table(
    uniqueConstraints = [
        UniqueConstraint(columnNames = ["name"]),
        UniqueConstraint(columnNames = ["webDomain"])
    ]
)
class Tenant {
    @Id
    @GeneratedValue
    @JsonIgnore
    var id: Long? = null

    @NotBlank
    @Size(min = 4, max = 30)
    lateinit var name: String

    @NotBlank
    @Length(min = 4, max = 25)
    @Pattern(regexp = "^(?!-)[A-Za-z0-9-]+([\\-\\.]{1}[a-z0-9]+)*\\.[A-Za-z]{2,6}$")
    lateinit var webDomain: String

    var dateCreated: Date? = null
    var lastModified: Date? = null

    @PreUpdate
    @PrePersist
    fun updateTimestamps() {
        lastModified = Date()
        if (dateCreated == null) {
            dateCreated = Date()
        }
    }
}