import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import io.gitlab.arturbosch.detekt.Detekt

buildscript {
    repositories {
        mavenCentral()
        maven("https://plugins.gradle.org/m2/")
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
        sourceCompatibility = JavaVersion.VERSION_11.toString()
        targetCompatibility = JavaVersion.VERSION_11.toString()
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")

            // temporary 1.8 target for local running
            jvmTarget = JavaVersion.VERSION_1_8.toString()
            incremental = false
        }
    }
}

subprojects {
    apply {
        plugin("io.spring.dependency-management")
        plugin("io.gitlab.arturbosch.detekt")
    }

    repositories {
        mavenCentral()
        maven {
            //for khttp depends
            url = uri("https://repo.spring.io/libs-release/")
        }
        jcenter()
        maven("https://plugins.gradle.org/m2/")
    }

    apply {
        plugin("io.spring.dependency-management")
        plugin("org.jlleitschuh.gradle.ktlint")
    }
}
