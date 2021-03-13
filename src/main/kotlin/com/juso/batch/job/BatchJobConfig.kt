package com.juso.batch.job

import com.juso.batch.domain.MatchBuild
import org.slf4j.Logger
import org.springframework.batch.core.Job
import org.springframework.batch.core.JobExecution
import org.springframework.batch.core.JobExecutionListener
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepScope
import org.springframework.batch.item.file.FlatFileItemReader
import org.springframework.batch.item.file.LineMapper
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper
import org.springframework.batch.item.file.mapping.DefaultLineMapper
import org.springframework.batch.item.file.mapping.FieldSetMapper
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.FileSystemResource


@Configuration
@EnableBatchProcessing
class BatchJobConfig(
        private val matchBuildProcessor: MatchBuildProcessor,
        private val matchBuildWriter: MatchBuildWriter,
        private val stepBuilderFactory: StepBuilderFactory,
        private val jobBuilderFactory: JobBuilderFactory,
        private val log: Logger
) {

    @Bean
    fun matchBuildReader(): FlatFileItemReader<Map<String, String>> {
        val flatFileItemReader = FlatFileItemReader<Map<String, String>>()
        flatFileItemReader.setResource(FileSystemResource("match_build.TXT"))
        flatFileItemReader.setLinesToSkip(1)
        flatFileItemReader.setEncoding("euc-kr")
        val delimitedLineTokenizer = DelimitedLineTokenizer()
        delimitedLineTokenizer.setDelimiter("|")
        delimitedLineTokenizer.setNames("eupMyunDongCode", "siDoNm", "siGunGuNm",
                "eupMyunDongNm", "roadNmCode", "roadNm",
                "underYn", "buildingMain", "buildingSub",
                "zipCode", "managementNo", "siGunGuBuilding",
                "buildingSep", "adminDongCode", "adminDongName",
                "upper", "lower", "coYn",
                "buildingCount", "buildingNmDetail", "bldNmChangeHistory",
                "bldNmChangeHisDetail", "liveYn", "centerLat",
                "centerLon", "entLat", "entLon",
                "siDoNmEnt", "siGunGuNmEng", "eupMyunDongNmEng",
                "roadNmEng", "eupMyunDongYn", "changeCode")
        val defaultLineMapper = DefaultLineMapper<Map<String, String>>()
        defaultLineMapper.setLineTokenizer(delimitedLineTokenizer)
        val beanWrapperFieldSetMapper = BeanWrapperFieldSetMapper<Map<String, String>>()
        // todo 여기서 mapper data class 만들어줘서 넣어줘야 한다.
        beanWrapperFieldSetMapper.setTargetType(Map)
        defaultLineMapper.setFieldSetMapper(beanWrapperFieldSetMapper)
        flatFileItemReader.setLineMapper(defaultLineMapper)
        return flatFileItemReader
//        return FlatFileItemReaderBuilder<Map<String, String>>()
//                .name("matchBuild")
//                .linesToSkip(1)
//                .delimited()
//                .lineTokenizer()
//                .build()
    }


    @Bean
    fun jobConfig(): Job {
        return jobBuilderFactory.get("matchBuildJob")
                .listener(jobExecutionListener())
                .flow(getInitState())
                .next(stepConfig())
                .end()
                .build()
    }

    @Bean
    fun getInitState(): Step {
        return stepBuilderFactory
                .get("stepInit")
                .tasklet { stepContribution, chunkContext ->
                    println("This is a batch job in Kotlin")
                    RepeatStatus.FINISHED
                }
                .build()
    }

    @Bean
    fun stepConfig(): Step {
        return stepBuilderFactory.get("matchBuildStep")
                .chunk<Map<String, String>, MatchBuild>(500)
                .reader(matchBuildReader())
                .processor(matchBuildProcessor)
                .writer(matchBuildWriter)
                .build()
    }

    @Bean
    fun jobExecutionListener(): JobExecutionListener {
        return object : JobExecutionListener {
            override fun beforeJob(jobExecution: JobExecution) {
                log.info("시작")
            }

            override fun afterJob(jobExecution: JobExecution) {
                log.info("끝")
            }
        }
    }
}