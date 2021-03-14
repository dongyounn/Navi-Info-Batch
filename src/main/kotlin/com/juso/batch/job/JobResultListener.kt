package com.juso.batch.job

import org.slf4j.Logger
import org.springframework.batch.core.JobExecution
import org.springframework.batch.core.JobExecutionListener
import org.springframework.stereotype.Component

@Component
class JobResultListener(
        private val log: Logger
) : JobExecutionListener {
    override fun beforeJob(jobExecution: JobExecution) {
        log.info("시작")
    }

    override fun afterJob(jobExecution: JobExecution) {
        log.info("끝")
    }
}