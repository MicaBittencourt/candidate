import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.5.5"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.5.31"
	kotlin("plugin.spring") version "1.5.31"
	kotlin("plugin.jpa") version "1.3.72"
}

group = "io.redspark"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
	jcenter()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web:2.5.5")
	implementation("org.springframework.boot:spring-boot-starter-validation:2.5.5")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.5.5")
	implementation("org.springframework.boot:spring-boot-starter-security:2.5.5")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.0")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.liquibase:liquibase-core:4.4.3")
	implementation("io.springfox:springfox-boot-starter:3.0.0")
	implementation("com.nimbusds:nimbus-jose-jwt:9.15.2")
	implementation("au.com.console:kotlin-jpa-specification-dsl:2.0.0-rc.1")

	developmentOnly("org.springframework.boot:spring-boot-devtools:2.5.5")

	runtimeOnly("org.postgresql:postgresql:42.2.24.jre7")

	testImplementation("org.springframework.boot:spring-boot-starter-test:2.5.5")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
