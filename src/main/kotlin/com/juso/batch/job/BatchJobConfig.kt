package com.juso.batch.job

import com.juso.batch.domain.JibunBuildDataModel
import com.juso.batch.domain.MatchBuild
import com.juso.batch.domain.MatchBuildDataModel
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.item.file.FlatFileItemReader
import org.springframework.batch.item.file.MultiResourceItemReader
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder
import org.springframework.batch.item.file.mapping.RecordFieldSetMapper
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.Resource


@Configuration
@EnableBatchProcessing
class BatchJobConfig(
        private val matchBuildProcessor: MatchBuildProcessor,
        private val matchBuildWriter: MatchBuildWriter,
        private val stepBuilderFactory: StepBuilderFactory,
        private val jobBuilderFactory: JobBuilderFactory,
        private val jobResultListener: JobResultListener,
        private val chunkResultListener: ChunkResultListener
) {
    @Value("\${chunk.size}")
    private val chunkSize = 500

    @Value("file:/Users/dongyoun_shin/IdeaProjects/batch/batchdata/match_build_*.txt")
    private val matchBuildFiles: Array<Resource> = arrayOf()

    @Value("file:/Users/dongyoun_shin/IdeaProjects/batch/batchdata/match_jibun*.txt")
    private val jibunBuildFiles: Array<Resource> = arrayOf()

    @Bean
    fun matchMultiResourceItemReader(): MultiResourceItemReader<MatchBuildDataModel> {
        val reader: MultiResourceItemReader<MatchBuildDataModel> = MultiResourceItemReader()
        reader.setDelegate(matchBuildReader())
        reader.setResources(matchBuildFiles)
        return reader
    }

    @Bean
    fun jibunMultiResourceItemReader(): MultiResourceItemReader<JibunBuildDataModel> {
        val reader: MultiResourceItemReader<JibunBuildDataModel> = MultiResourceItemReader()
        reader.setDelegate(jiBunReader())
        reader.setResources(jibunBuildFiles)
        return reader
    }

    @Bean
    fun matchBuildReader(): FlatFileItemReader<MatchBuildDataModel> {
        return FlatFileItemReaderBuilder<MatchBuildDataModel>()
                .name("matchBuildReader")
                .encoding("euc-kr")
                .delimited().delimiter("|")
                .names("eupMyunDongCode", "siDoNm", "siGunGuNm",
                        "eupMyunDongNm", "roadNmCode", "roadNm",
                        "underYn", "buildingMain", "buildingSub",
                        "zipCode", "managementNo", "siGunGuBuilding",
                        "buildingSep", "adminDongCode", "adminDongName",
                        "upper", "lower", "coYn",
                        "buildingCount", "buildingNmDetail", "bldNmChangeHistory",
                        "bldNmChangeHisDetail", "liveYn", "centerLat",
                        "centerLon", "entLat", "entLon",
                        "siDoNmEng", "siGunGuNmEng", "eupMyunDongNmEng",
                        "roadNmEng", "eupMyunDongYn", "changeCode")
                .fieldSetMapper(RecordFieldSetMapper<MatchBuildDataModel>(MatchBuildDataModel::class.java))
                .build()
    }

    @Bean
    fun jiBunReader(): FlatFileItemReader<JibunBuildDataModel> {
        return FlatFileItemReaderBuilder<JibunBuildDataModel>()
                .name("jiBunMatch")
                .encoding("euc-kr")
                .delimited().delimiter("|")
                .names("legalDongCode", "siDoNm", "siGunGuNm",
                        "eupMyunDongNm", "liNm", "mntYn",
                        "mainJiNum", "subJiNum", "roadNmCode",
                        "underYn", "buildMain", "buildSub",
                        "jiBunSerialNo", "siDoNmEng", "siGunGuNmEng",
                        "eupMyunDongNmEng", "liNmEng", "changeReason",
                        "buildManagementNo", "jusoAreaCode")
                .fieldSetMapper(RecordFieldSetMapper<JibunBuildDataModel>(JibunBuildDataModel::class.java))
                .build()
    }


    @Bean
    fun jobConfig(): Job {
        return jobBuilderFactory.get("matchBuildJob")
                .listener(jobResultListener)
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
                .chunk<MatchBuildDataModel, MatchBuild>(chunkSize)
                .reader(matchMultiResourceItemReader())
                .processor(matchBuildProcessor)
                .writer(matchBuildWriter)
                .listener(chunkResultListener)
                .build()
    }
}