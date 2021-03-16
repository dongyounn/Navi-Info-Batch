package com.juso.batch.domain

import java.math.BigDecimal
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "MATCH_BUILD")
data class MatchBuild(
        @Id
        val managementNo: String,
        var eupMyunDongCode: String?,
        var siDoNm: String?,
        var siGunGuNm: String?,
        var eupMyunDongNm: String?,
        var roadNmCode: String?,
        var roadNm: String?,
        var underYn: String?,
        var buildingMain: Long?,
        var buildingSub: Long?,
        var zipCode: Long?,
        var siGunGuBuilding: String?,
        var buildingSep: String?,
        var adminDongCode: String?,
        var adminDongName: String?,
        var upper: Long?,
        var lower: Long?,
        var coYn: String?,
        var buildingCount: Long?,
        var buildingNmDetail: String?,
        var bldNmChangeHistory: String?,
        var bldNmChangeHisDetail: String?,
        var liveYn: String?,
        var centerLat: BigDecimal?,
        var centerLon: BigDecimal?,
        var entLat: BigDecimal?,
        var entLon: BigDecimal?,
        var siDoNmEng: String?,
        var siGunGuNmEng: String?,
        var eupMyunDongNmEng: String?,
        var roadNmEng: String?,
        var eupMyunDongYn: String?,
        var changeCode: String?,

        var centerLatitude: BigDecimal?,
        var centerLongitude: BigDecimal?,
        var enterLatitude: BigDecimal?,
        var enterLongitude: BigDecimal?
) {
    fun ofCenterLocation(
            latitude: BigDecimal?,
            longitude: BigDecimal?
    ): MatchBuild {
        this.centerLatitude = latitude
        this.centerLongitude = longitude
        return this
    }

    fun ofEnterLocation(
            latitude: BigDecimal?,
            longitude: BigDecimal?
    ): MatchBuild {
        this.enterLatitude = latitude
        this.enterLongitude = longitude
        return this
    }

}
