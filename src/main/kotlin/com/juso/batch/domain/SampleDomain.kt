package com.juso.batch.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "SAMPLE")
data class SampleDomain(
        @Id
        @Column(name = "USER_ID")
        val id: String,
        @Column(name = "NAME")
        val name: String
)