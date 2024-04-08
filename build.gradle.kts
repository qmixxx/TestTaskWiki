import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.21"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib") // Kotlin standard library dependency
    implementation("io.rest-assured:rest-assured:4.4.0") // RestAssured dependency
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.1") // JUnit 5 dependency for testing
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.1") // JUnit 5 runtime dependency for testing
    implementation("org.json:json:20240303")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}