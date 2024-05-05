import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.21"
    application
    id("com.github.johnrengelman.shadow") version "4.0.4"
    id ("com.github.ben-manes.versions") version "0.39.0"
   
}

application {
    // Ensure the main class name is correctly referenced
    mainClassName = "MainKt"
}

repositories {
    // Replace jcenter() with mavenCentral()
    mavenCentral()
}

dependencies {
    // Update to use the 'implementation' keyword which is preferred over 'compile'
    implementation(kotlin("stdlib-jdk8"))

    // Add Ktor dependencies
    implementation("io.ktor:ktor-server-netty:1.6.7")
    implementation("ch.qos.logback:logback-classic:1.2.3")
    implementation("io.ktor:ktor-server-core:1.6.7")
    implementation("io.ktor:ktor-http-jvm:1.6.7")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<Jar> {
    // Ensure the application can find the main class when running the shadow JAR
    manifest {
        attributes("Main-Class" to application.mainClassName)
    }

    // Configure the shadow JAR plugin to include all dependencies in the JAR
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
}

