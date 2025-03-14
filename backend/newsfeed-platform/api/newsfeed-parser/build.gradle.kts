plugins {
    id("java")
}

group = "com.learn.newsfeed"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.saxon.parser)
    implementation(libs.snakeyaml)
    implementation(libs.jackson.databind)
    testImplementation(libs.mockito.core)
    implementation(project(":libraries:logging-util"))
    implementation(project(":libraries:cloud-storage-lib"))
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}