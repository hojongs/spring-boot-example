import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    idea
    id("org.springframework.boot") version "2.4.4"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.4.31"
    kotlin("plugin.spring") version "1.4.31"
    jacoco
}

group = "com.hojong.springbootexample"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "11"
        }
    }

    withType<Test> {
        useJUnitPlatform()
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation(kotlin("reflect"))
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")

    // actuator
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    runtimeOnly("io.micrometer:micrometer-registry-prometheus")
}

configurations.all {
    exclude(group = "org.junit.vintage")
}

// configure for jacoco
tasks {
    withType<Test> {
        finalizedBy(jacocoTestReport)
    }

    withType<JacocoReport> {
        dependsOn(test)

        classDirectories.setFrom(
            files(
                classDirectories.files.map { baseDir ->
                    fileTree(baseDir)
                        .exclude(
                            "**/MainKt.class"
                        )
                }
            )
        )

        // print each class file names
//        classDirectories.files
//            .map { it.name }
//            .forEach { println(it) }
    }
}
