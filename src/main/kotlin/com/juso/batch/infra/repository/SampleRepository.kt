package com.juso.batch.infra.repository

import com.juso.batch.domain.SampleDomain
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SampleRepository : JpaRepository<SampleDomain, String> {

}