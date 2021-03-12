package com.juso.batch.config

import org.springframework.batch.core.configuration.annotation.BatchConfigurer
import org.springframework.batch.core.explore.JobExplorer
import org.springframework.batch.core.explore.support.MapJobExplorerFactoryBean
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.batch.core.launch.support.SimpleJobLauncher
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import org.springframework.transaction.PlatformTransactionManager

@Component
class BatchConfig(
        @Qualifier("CustomTransactionManager") val platformTransactionManager: PlatformTransactionManager
) : BatchConfigurer {

    private val mapJobRepositoryFactoryBean = MapJobRepositoryFactoryBean(transactionManager).also { it.afterPropertiesSet() }

    private val jobRepository = mapJobRepositoryFactoryBean.`object`

    private val jobExplorer = MapJobExplorerFactoryBean(mapJobRepositoryFactoryBean).also { it.afterPropertiesSet() }.`object`

    private val jobLauncher = SimpleJobLauncher().also {
        it.setJobRepository(jobRepository)
        it.afterPropertiesSet()
    }

    override fun getJobRepository(): JobRepository = jobRepository

    override fun getJobLauncher(): JobLauncher = jobLauncher

    override fun getJobExplorer(): JobExplorer = jobExplorer

    override fun getTransactionManager(): PlatformTransactionManager = platformTransactionManager
}