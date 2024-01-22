plugins {
    kotlin("jvm") version "1.7.10"
    kotlin("plugin.jpa") version "1.7.10"
    kotlin("plugin.spring") version "1.7.10"
    id("org.springframework.boot") version "3.0.2"
    id("io.spring.dependency-management") version "1.0.14.RELEASE"
    id("org.openapi.generator") version "7.0.1"
    application
    `java-library`
}

group = "io.swilliams.finch"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven { url = uri("https://repo.spring.io/milestone") }
}

dependencies {
    testImplementation(kotlin("test"))

    // Generated
    val kotlinxCoroutinesVersion = "1.6.1"
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinxCoroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:$kotlinxCoroutinesVersion")
    implementation("org.springdoc:springdoc-openapi-starter-webflux-ui:2.0.0-M5")

    implementation("com.google.code.findbugs:jsr305:3.0.2")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("jakarta.validation:jakarta.validation-api")
    implementation("jakarta.annotation:jakarta.annotation-api:2.1.0")

    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(module = "junit")
    }
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$kotlinxCoroutinesVersion")
}

tasks.compileKotlin {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass.set("MainKt")
}

openApiGenerate {
    cleanupOutput.set(true)
    generatorName.set("kotlin-spring")
    inputSpec.set("$projectDir/openapi.yml")
    outputDir.set("$buildDir/generated")
    apiPackage.set("$group.api")
    invokerPackage.set("$group.api.invoker")
    modelPackage.set("$group.api.model")
    globalProperties.set(
        mapOf(
            "modelDocs" to "false",
        ),
    )
    configOptions.set(
        mapOf(
            "useSpringBoot3" to "true",
            "interfaceOnly" to "true",
            "reactive" to "true",
            "input-invalidation" to "true",
        ),
    )
}

kotlin {
    sourceSets {
        named("main") {
            kotlin.srcDir("$buildDir/generated/src/main/kotlin")
        }
    }
}

