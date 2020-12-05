plugins {
    kotlin("jvm") version "1.4.20"
    application
}

repositories {
    jcenter()
}


dependencies {
    // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation(platform("com.fasterxml.jackson:jackson-bom:2.11.3"))

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    implementation("io.ktor:ktor-server-core:1.4.3")
    implementation("io.ktor:ktor-server-netty:1.4.3")
    implementation("io.ktor:ktor-jackson:1.4.3")
    implementation("io.ktor:ktor-auth:1.4.3")
    implementation("io.ktor:ktor-auth-jwt:1.4.3")

    implementation("org.slf4j:slf4j-api:1.7.30")
    implementation("org.slf4j:jul-to-slf4j:1.7.30")
    implementation("ch.qos.logback:logback-classic:1.2.3")
    implementation("org.apache.logging.log4j:log4j-to-slf4j:2.13.3")

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-jackson:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.9.0")

    // Use the Kotlin test library.
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    // Use the Kotlin JUnit integration.
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}

application {
    mainClassName = "nl.toefel.MainKt"
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

configurations {
    implementation {
        resolutionStrategy.failOnVersionConflict()
    }
}

tasks {
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "11"
        }
    }
    test {
        testLogging.showExceptions = true
    }
}

///**
// * Fixes this error:
// *
// * Cannot inline bytecode built with JVM target 1.8 into bytecode that is being built with JVM target 1.6.
// * Please specify proper '-jvm-target' option
// */
//tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
//    kotlinOptions {
//        freeCompilerArgs = listOf("-Xjsr305=strict")
//        jvmTarget = "11"
//    }
//}
//
