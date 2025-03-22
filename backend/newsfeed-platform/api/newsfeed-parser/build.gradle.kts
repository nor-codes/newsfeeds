plugins {
    java
    id("org.springframework.boot") version "3.4.3"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "com.learn.newsfeed"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.spring.io/release")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

extra["springCloudVersion"] = "2024.0.0"

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

dependencies {
    implementation(libs.saxon.parser)
    implementation(libs.snakeyaml)
    implementation(libs.jackson.databind)
    implementation(platform(libs.amazon.software.bom))
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("software.amazon.awssdk:sqs")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation(libs.mockito.core)
    implementation(project(":libraries:logging-util"))
    implementation(project(":libraries:cloud-storage-lib"))
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}


tasks.test {
    useJUnitPlatform()
}