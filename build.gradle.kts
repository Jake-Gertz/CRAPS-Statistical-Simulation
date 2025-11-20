plugins {
    kotlin("jvm") version "2.1.10"
    id("application")
}

group = "crapsSimulation"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}

application {
    mainClass.set("crapsSimulation.MainKt")
}