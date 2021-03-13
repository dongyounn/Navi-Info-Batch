package com.juso.batch.infra.repository

import com.juso.batch.domain.MatchBuild
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MatchBuildRepository : JpaRepository<MatchBuild, String> {
    fun findByManagementNo(req: String): MatchBuild?
}