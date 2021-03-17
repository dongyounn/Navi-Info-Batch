package com.juso.batch.infra.repository

import com.juso.batch.domain.JibunBuild
import com.juso.batch.domain.JibunPk
import com.juso.batch.domain.MatchBuild
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MatchBuildRepository : JpaRepository<MatchBuild, String> {
    fun findByManagementNo(req: String): MatchBuild?
}

@Repository
interface JibunMatchRepository : JpaRepository<JibunBuild, JibunPk> {
    fun findByJiBunId(req: JibunPk): JibunBuild?
}