package com.juso.batch.domain

import java.io.Serializable
import java.math.BigDecimal
import javax.persistence.*

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


@Entity
@Table(name = "JIBUN_BUILD")
data class JibunBuild(
        @EmbeddedId
        val jiBunId: JibunPk,
        val legalDongCode: String?,
        val siDoNm: String?,
        val siGunGuNm: String?,
        val eupMyunDongNm: String?,
        val liNm: String?,
        val mntYn: String?,
        val mainJiNum: Long?,
        val subJiNum: String?,
        val siDoNmEng: String?,
        val siGunGuNmEng: String?,
        val eupMyunDongNmEng: String?,
        val liNmEng: String?,
        val changeReason: String?,
        val buildManagementNo: String?,
) {
    companion object {
        fun of(req: JibunBuildDataModel) = JibunBuild(
                legalDongCode = req.legalDongCode,
                siDoNm = req.siDoNm,
                siGunGuNm = req.siGunGuNm,
                eupMyunDongNm = req.eupMyunDongNm,
                liNm = req.liNm,
                mntYn = req.mntYn,
                mainJiNum = req.mainJiNum?.toLong(),
                subJiNum = req.subJiNum,
                siDoNmEng = req.siDoNmEng,
                siGunGuNmEng = req.siGunGuNmEng,
                eupMyunDongNmEng = req.eupMyunDongNmEng,
                liNmEng = req.liNmEng,
                changeReason = req.changeReason,
                buildManagementNo = req.buildManagementNo,
                jiBunId = JibunPk.of(req)
        )
    }
}

@Embeddable
data class JibunPk(
        var roadNmCode: String,
        var underYn: String,
        var buildMain: Long,
        var buildSub: Long,
        var jiBunSerialNo: Long,
        var jusoAreaCode: String
) : Serializable {
    companion object {
        fun of(req: JibunBuildDataModel) = JibunPk(
                roadNmCode = req.roadNmCode,
                underYn = req.underYn,
                buildMain = req.buildMain.toLong(),
                buildSub = req.buildSub.toLong(),
                jiBunSerialNo = req.jiBunSerialNo.toLong(),
                jusoAreaCode = req.jusoAreaCode
        )
    }
}