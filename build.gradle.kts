import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.21"
    application
}

apply(plugin = "idea")
apply(plugin = "org.jetbrains.kotlin.jvm")

group = "me.catgray"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.qameta.allure:allure-kotlin-commons:2.4.0")
    implementation("io.qameta.allure:allure-rest-assured:2.18.1")
    implementation("io.qameta.allure:allure-kotlin-model:2.4.0")
    implementation("io.github.microutils:kotlin-logging:2.1.23")
    implementation("io.rest-assured:rest-assured:5.1.1")
    implementation("org.assertj:assertj-core:3.23.1")
    implementation("com.google.code.gson:gson:2.9.1")

    testImplementation(kotlin("test"))
    testImplementation("io.qameta.allure:allure-junit5:2.18.1")
    testImplementation("org.awaitility:awaitility-kotlin:4.2.0")
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.0")

}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}