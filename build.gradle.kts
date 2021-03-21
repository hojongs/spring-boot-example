plugins {
    kotlin("plugin.jpa") version "1.4.31"
    id("maven-publish")
}

group = "com.hojong"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("com.vladmihalcea:hibernate-types-52:2.10.3")
    runtimeOnly("io.micrometer:micrometer-registry-prometheus")
    testImplementation("org.assertj:assertj-core")
    testImplementation("com.ninja-squad:springmockk:3.0.1")

    // jpa
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("com.h2database:h2")

    // webflux
    implementation("io.projectreactor:reactor-tools")
}

java {
    sourceCompatibility = JavaVersion.VERSION_15
    withSourcesJar()
}

allOpen {
    annotation("javax.persistence.Entity")
}

publishing {
    publications {
        create<MavenPublication>(project.name) {
            from(components["java"])
        }
    }

    repositories {
        maven {
            name = "gpr"
            credentials {
                username = System.getenv("GPR_USERNAME")
                password = System.getenv("GPR_TOKEN")
            }
            url = uri("https://maven.pkg.github.com/hojongs/${project.name}")
        }
    }
}
