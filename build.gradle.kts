import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath("gradle.plugin.com.ewerk.gradle.plugins:querydsl-plugin:1.0.10")
    }
}

plugins {
    val kotlinVersion = "1.4.30"
    id("org.springframework.boot") version "2.4.3"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("plugin.jpa") version kotlinVersion
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
    kotlin("kapt") version kotlinVersion
}

group = "com.juso"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.querydsl:querydsl-jpa:4.4.0")
    implementation("com.querydsl:querydsl-apt:4.4.0")
    implementation("com.querydsl:querydsl-sql-spring:4.4.0")
    kapt("com.querydsl:querydsl-apt:4.4.0:jpa")
    implementation("org.springframework.boot:spring-boot-starter-batch")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("ojdbc7-12.1.0.2.jar"))))
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.osgeo:proj4j:0.1.0")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.batch:spring-batch-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

val querydslSourcesDir = file("src/main/generated")

sourceSets {
    main {
        java {
            listOf("src/main/java", querydslSourcesDir)
        }
        kotlin {
            listOf("src/main/kotlin", querydslSourcesDir)
        }
    }
}