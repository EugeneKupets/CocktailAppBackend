
plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.serialization)
    alias(ktorLibs.plugins.ktor)
    alias(libs.plugins.shadow)
}

group = "com.example"
version = "1.0.0-SNAPSHOT"

application {
    mainClass = "com.example.MainKt"
}

dependencies {
    implementation("io.ktor:ktor-server-core")
    implementation("io.ktor:ktor-server-netty")
    implementation("io.ktor:ktor-server-content-negotiation")
    implementation("io.ktor:ktor-serialization-kotlinx-json")
    implementation("io.ktor:ktor-server-cors")

    // MongoDB
    implementation("org.mongodb:mongodb-driver-kotlin-coroutine:4.11.0")

    // Logging
    implementation(libs.logback.classic)
}
