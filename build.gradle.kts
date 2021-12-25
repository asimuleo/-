import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.10"
    application
}

group = "me.csjav"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

    implementation(group = "javax.xml.bind", name = "jaxb-api", version = "2.3.1")

    implementation("org.seleniumhq.selenium:selenium-java:4.1.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0-RC3")

    implementation(group = "org.apache.logging.log4j", name = "log4j-slf4j-impl", version = "2.17.0")

    // https://github.com/doyaaaaaken/kotlin-csv
    implementation("com.github.doyaaaaaken:kotlin-csv-jvm:1.2.0")

    // ktor
    implementation("io.ktor:ktor-client-core:1.6.7")
    implementation("io.ktor:ktor-client-cio:1.6.7")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

application {
    mainClass.set("MainKt")
}