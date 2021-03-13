package com.juso.batch.job

import com.fasterxml.jackson.databind.ObjectMapper
import com.juso.batch.domain.MatchBuild
import org.springframework.batch.item.ItemProcessor
import org.springframework.stereotype.Component

@Component
class MatchBuildProcessor(
        private val objectMapper: ObjectMapper
) : ItemProcessor<Map<String, String>, MatchBuild> {
    override fun process(item: Map<String, String>): MatchBuild {
        return objectMapper.convertValue(item, MatchBuild::class.java)
    }
}