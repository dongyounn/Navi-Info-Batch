package com.juso.batch.job

import com.juso.batch.domain.MatchBuild
import com.juso.batch.infra.repository.MatchBuildRepository
import org.springframework.batch.item.ItemWriter
import org.springframework.stereotype.Component

@Component
class MatchBuildWriter(
        private val matchBuildRepository: MatchBuildRepository
) : ItemWriter<MatchBuild> {
    override fun write(items: MutableList<out MatchBuild>) {
        items.forEach { target ->
            matchBuildRepository.save(target)
        }
    }
}