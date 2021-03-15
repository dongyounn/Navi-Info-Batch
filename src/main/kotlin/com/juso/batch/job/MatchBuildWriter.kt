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
            /* TODO 이력 관리 하려면 어떻게 해야할까? */
            matchBuildRepository.findByManagementNo(target.managementNo)?.let {
//                여기서 위경도 변경 로직 추가하자
                matchBuildRepository.save(target)
            } ?: matchBuildRepository.save(target)

        }
    }
}