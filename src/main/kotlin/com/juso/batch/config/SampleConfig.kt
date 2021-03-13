//package com.juso.batch.config
//
//import com.juso.batch.domain.SampleDomain
//import com.juso.batch.infra.repository.SampleRepository
//import org.slf4j.LoggerFactory
//import org.springframework.batch.core.Job
//import org.springframework.batch.core.JobExecution
//import org.springframework.batch.core.JobExecutionListener
//import org.springframework.batch.core.Step
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
//import org.springframework.batch.repeat.RepeatStatus
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.stereotype.Component
//
//
//@Configuration
//@EnableBatchProcessing
//@Component
//class SampleConfig(
//        private val stepBuilderFactory: StepBuilderFactory,
//        private val jobBuilderFactory: JobBuilderFactory,
//        private val sampleRepository: SampleRepository
//) {
//    private val log = LoggerFactory.getLogger(this::class.java)
//
//    @Bean
//    fun migrationUserInfo(): Job {
//        return jobBuilderFactory.get("sampleJob")
//                .listener(jobExecutionListener())
//                .flow(getInitState())
//                .end()
//                .build()
//    }
//
//    @Bean
//    fun getInitState(): Step {
//        return stepBuilderFactory
//                .get("stepInit")
//                .tasklet { stepContribution, chunkContext ->
//                    println("This is a batch job in Kotlin")
//                    RepeatStatus.FINISHED
//                }
//                .build()
//    }
//
//    @Bean
//    fun jobExecutionListener(): JobExecutionListener {
//        return object : JobExecutionListener {
//            override fun beforeJob(jobExecution: JobExecution) {
//                log.info("시작")
//            }
//
//            override fun afterJob(jobExecution: JobExecution) {
//                sampleRepository.save(SampleDomain("3", "1"))
//                log.info("끝")
//            }
//        }
//    }
//}