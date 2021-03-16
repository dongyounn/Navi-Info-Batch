package com.juso.batch.job

import org.osgeo.proj4j.*
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class ConvertLocationInfo {
    private lateinit var trans: CoordinateTransform

    fun CRSConverter(utmX: BigDecimal, utmY: BigDecimal): ProjCoordinate {
        val srcCRS: CoordinateReferenceSystem = CRSFactory().createFromName("EPSG:5179")
        val dstCRS = CRSFactory().createFromName("EPSG:4326")

        val ctFactory = CoordinateTransformFactory()
        this.trans = ctFactory.createTransform(srcCRS, dstCRS)
        return transform(utmX, utmY);
    }

    fun transform(coordX: BigDecimal, coordY: BigDecimal): ProjCoordinate {
        val srcCoord = ProjCoordinate()
        val dstCoord = ProjCoordinate()

        srcCoord.x = coordX.toDouble()
        srcCoord.y = coordY.toDouble()

        return trans.transform(srcCoord, dstCoord)

    }
}