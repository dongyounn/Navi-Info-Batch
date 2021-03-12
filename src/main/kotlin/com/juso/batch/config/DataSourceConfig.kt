package com.juso.batch.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.transaction.PlatformTransactionManager
import javax.persistence.EntityManagerFactory

@Configuration
class DataSourceConfig {
    @Bean
    @Primary
    fun getDataSource(hikariProperties: HikariProperties): HikariDataSource {
        val password = hikariProperties.password

        val hikariConfig = HikariConfig()
        hikariConfig.username = hikariProperties.username
        hikariConfig.password = password
        hikariConfig.jdbcUrl = hikariProperties.jdbcUrl
        hikariConfig.driverClassName = hikariProperties.driverClassName
        hikariConfig.validationTimeout = hikariProperties.validationTimeout
        hikariConfig.minimumIdle = hikariProperties.minimumIdle
        hikariConfig.maximumPoolSize = hikariProperties.maximumPoolSize

        return HikariDataSource(hikariConfig)
    }


    @Bean("CustomTransactionManager")
    fun transactionManager(entityManagerFactory: EntityManagerFactory): PlatformTransactionManager {
        val transactionManager = JpaTransactionManager(entityManagerFactory)
        transactionManager.entityManagerFactory = entityManagerFactory
        return transactionManager
    }
}