package com.juso.batch.domain


data class MatchBuildDataModel(
        val eupMyunDongCode: String?,
        val siDoNm: String?,
        val siGunGuNm: String?,
        val eupMyunDongNm: String?,
        val roadNmCode: String?,
        val roadNm: String?,
        val underYn: String?,
        val buildingMain: String?,
        val buildingSub: String?,
        val zipCode: String?,
        val managementNo: String?,
        val siGunGuBuilding: String?,
        val buildingSep: String?,
        val adminDongCode: String?,
        val adminDongName: String?,
        val upper: String?,
        val lower: String?,
        val coYn: String?,
        val buildingCount: String?,
        val buildingNmDetail: String?,
        val bldNmChangeHistory: String?,
        val bldNmChangeHisDetail: String?,
        val liveYn: String?,
        val centerLat: String?,
        val centerLon: String?,
        val entLat: String?,
        val entLon: String?,
        val siDoNmEng: String?,
        val siGunGuNmEng: String?,
        val eupMyunDongNmEng: String?,
        val roadNmEng: String?,
        val eupMyunDongYn: String?,
        val changeCode: String?
)


data class JibunBuildDataModel(
        val legalDongCode: String?,
        val siDoNm: String?,
        val siGunGuNm: String?,
        val eupMyunDongNm: String?,
        val liNm: String?,
        val mntYn: String?,
        val mainJiNum: String?,
        val subJiNum: String?,
        val roadNmCode: String,
        val underYn: String,
        val buildMain: String,
        val buildSub: String,
        val jiBunSerialNo: String,
        val siDoNmEng: String?,
        val siGunGuNmEng: String?,
        val eupMyunDongNmEng: String?,
        val liNmEng: String?,
        val changeReason: String?,
        val buildManagementNo: String?,
        val jusoAreaCode: String
)

