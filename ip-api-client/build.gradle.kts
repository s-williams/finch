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
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.10")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.8.10")
    implementation("com.squareup.moshi:moshi-kotlin:1.14.0")
    implementation("com.squareup.moshi:moshi-adapters:1.14.0")
    implementation("com.squareup.okhttp3:okhttp:4.11.0")
    testImplementation("io.kotlintest:kotlintest-runner-junit5:3.4.2")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
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
    generatorName.set("kotlin")
    inputSpec.set("$projectDir/openapi.yml")
    outputDir.set("$buildDir/generated")
    apiPackage.set("$group.ipapi")
    invokerPackage.set("$group.ipapi.invoker")
    modelPackage.set("$group.ipapi.model")
    globalProperties.set(
        mapOf(
            "modelDocs" to "false",
        ),
    )
    configOptions.set(
        mapOf(
            "sortParamsByRequiredFlag" to "true",
            "enumPropertyNaming" to "original",
            "useCoroutines" to "true",
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