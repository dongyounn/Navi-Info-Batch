package com.juso.batch.job

import com.juso.batch.domain.FileDataModel
import com.juso.batch.domain.MatchBuild
import org.slf4j.Logger
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

    @Value("file:/Users/dongyoun_shin/IdeaProjects/batch/batchdata/*.txt")
    private val inputFiles: Array<Resource> = arrayOf()

    @Bean
    fun multiResourceItemReader(): MultiResourceItemReader<FileDataModel> {
        val reader: MultiResourceItemReader<FileDataModel> = MultiResourceItemReader()
        reader.setDelegate(matchBuildReader())
        reader.setResources(inputFiles)
        return reader
    }

    @Bean
    fun matchBuildReader(): FlatFileItemReader<FileDataModel> {
        return FlatFileItemReaderBuilder<FileDataModel>()
                .name("matchBuildReader")
//                .resource(FileSystemResource("match_build.TXT"))
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
                .fieldSetMapper(RecordFieldSetMapper<FileDataModel>(FileDataModel::class.java))
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
                .listener(chunkResultListener)
                .chunk<FileDataModel, MatchBuild>(chunkSize)
                .reader(multiResourceItemReader())
                .processor(matchBuildProcessor)
                .writer(matchBuildWriter)
                .build()
    }
}