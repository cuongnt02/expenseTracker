plugins {
    kotlin("jvm") version "2.1.21"
    application
}

group = "com.ntc"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("com.github.ajalt.clikt:clikt:5.0.1")

    // optional support for rendering markdown in help messages
    implementation("com.github.ajalt.clikt:clikt-markdown:5.0.1")
}

application {
    mainClass = "com.ntc.expenseTracker.App"
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}