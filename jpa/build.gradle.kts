import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	idea
	id("org.springframework.boot") version "2.4.4"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.4.31"
	kotlin("plugin.spring") version "1.4.31"
	kotlin("plugin.jpa") version "1.4.31"
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
	implementation(kotlin("reflect"))
	implementation(kotlin("stdlib-jdk8"))

	// jpa
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	runtimeOnly("com.h2database:h2")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

allOpen {
	annotations(
		"javax.persistence.Entity"
	)
}
