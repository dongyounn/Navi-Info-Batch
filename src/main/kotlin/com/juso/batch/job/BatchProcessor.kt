package com.juso.batch.job

import com.fasterxml.jackson.databind.ObjectMapper
import com.juso.batch.domain.JibunBuild
import com.juso.batch.domain.JibunBuildDataModel
import com.juso.batch.domain.MatchBuildDataModel
import com.juso.batch.domain.MatchBuild
import org.springframework.batch.item.ItemProcessor
import org.springframework.stereotype.Component

@Component
class MatchBuildProcessor(
        private val objectMapper: ObjectMapper
) : ItemProcessor<MatchBuildDataModel, MatchBuild> {
    override fun process(item: MatchBuildDataModel): MatchBuild {
        return objectMapper.convertValue(item, MatchBuild::class.java)
    }
}

@Component
class JibunBuildProcessor : ItemProcessor<JibunBuildDataModel, JibunBuild> {
    override fun process(item: JibunBuildDataModel): JibunBuild {
        return JibunBuild.of(item)
    }
}