package com.juso.batch.domain

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "MATCH_BUILD")
data class MatchBuild(
        @Id
        val managementNo: String,
        val eupMyunDongCode: String,
        val siDoNm: String,
        val siGunGuNm: String

)