import org.jlleitschuh.gradle.ktlint.reporter.ReporterType.CHECKSTYLE
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType.HTML

plugins {
    id("org.springframework.boot")
    id("groovy")
    id("org.jlleitschuh.gradle.ktlint")

    kotlin("jvm")
    kotlin("plugin.spring")
}

ktlint {
    verbose.set(true)
    outputToConsole.set(true)
    coloredOutput.set(true)
    reporters {
        reporter(CHECKSTYLE)
        reporter(HTML)
    }
    filter {
        exclude("**/style-violations.kt")
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("khttp:khttp:1.0.0")
    implementation("org.codehaus.groovy:groovy-all:3.0.3")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation("io.springfox:springfox-swagger-ui:2.9.2")
    implementation("io.springfox:springfox-swagger2:2.9.2")
    testImplementation("org.spockframework:spock-core:1.3-groovy-2.5")
}
