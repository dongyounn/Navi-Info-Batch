package com.juso.batch.job

import org.slf4j.Logger
import org.springframework.batch.core.ChunkListener
import org.springframework.batch.core.ExitStatus
import org.springframework.batch.core.StepExecution
import org.springframework.batch.core.StepExecutionListener
import org.springframework.batch.core.annotation.AfterChunk
import org.springframework.batch.core.annotation.BeforeChunk
import org.springframework.batch.core.listener.StepExecutionListenerSupport
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class ChunkResultListener(
        private val log: Logger
) : ChunkListener {

    override fun afterChunk(context: ChunkContext) {
        log.info("### End Chunk Time : ${context.stepContext.stepExecution.lastUpdated}")
        log.info("### End Chunk Count: ${context.stepContext.stepExecution.readCount}")
        log.info("=====================================================================")
    }


    override fun afterChunkError(context: ChunkContext) {
    }

    override fun beforeChunk(context: ChunkContext) {
        log.info("=====================================================================")
        log.info("### Start Chunk Time : ${context.stepContext.stepExecution.startTime}")
    }
}