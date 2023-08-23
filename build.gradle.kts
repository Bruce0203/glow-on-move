import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.plugin.mpp.pm20.util.archivesName

plugins {
    kotlin("jvm") version "1.9.0"
    application
    id("org.jetbrains.kotlin.plugin.serialization") version "1.4.20"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "io.github.bruce0203.glowonmove"
version = ""

repositories {
    maven { url = uri("https://jitpack.io") }
    maven { url = uri("https://repo.papermc.io/repository/maven-public/") }
}

dependencies {
    compileOnly(rootProject.fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    compileOnly("io.papermc.paper:paper-api:1.19.2-R0.1-SNAPSHOT")
    implementation("com.charleskorn.kaml:kaml:0.55.0")
    implementation("io.github.skytasul:glowingentities:1.3")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

application {
    mainClass.set("MainKt")
}

tasks.withType<ShadowJar>() {
    archiveFileName = "${rootProject.name}.jar"
}