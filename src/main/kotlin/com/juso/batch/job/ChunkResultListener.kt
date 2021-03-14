package com.juso.batch.job

import org.slf4j.Logger
import org.springframework.batch.core.ExitStatus
import org.springframework.batch.core.StepExecution
import org.springframework.batch.core.StepExecutionListener
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class ChunkResultListener(
        private val log: Logger
) : StepExecutionListener {

    @Value("\${chunk.size}")
    private val chunkSize = 500

    private var count = 1

    override fun beforeStep(stepExecution: StepExecution) {
        log.info("### Start Chunk : ${chunkSize.times(count)}")
    }

    override fun afterStep(stepExecution: StepExecution): ExitStatus {
        log.info("### End Chunk : ${chunkSize.times(count)}")
        log.info("==========================================")
        count++
        return ExitStatus.COMPLETED
    }
}