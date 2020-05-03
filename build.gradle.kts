import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        mavenCentral()
        jcenter()
    }
    dependencies {
        classpath("org.jlleitschuh.gradle:ktlint-gradle:9.2.1")
    }
}

plugins {
    id("org.springframework.boot") version "2.2.0.RELEASE" apply false
    id("io.spring.dependency-management") version "1.0.8.RELEASE" apply false
    id("io.gitlab.arturbosch.detekt").version("1.8.0")

    kotlin("jvm") version "1.3.72" apply false
    kotlin("plugin.spring") version "1.3.50" apply false
}

apply(plugin = "org.jlleitschuh.gradle.ktlint")

allprojects {
    group = "com.kotlin"
    version = "1.3.72"

    tasks.withType<JavaCompile> {
        sourceCompatibility = "1.8"
        targetCompatibility = "1.8"
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "1.8"
            incremental = false
        }
    }
}

subprojects {
    repositories {
        mavenCentral()
        maven {
            //for khttp depends
            url = uri("https://repo.spring.io/libs-release/")
        }
        jcenter()
    }

    apply {
        plugin("io.spring.dependency-management")
        plugin("io.gitlab.arturbosch.detekt")
    }
}
