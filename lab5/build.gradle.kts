plugins {
    id("java")
    id("com.github.johnrengelman.shadow").version("8.1.1")
    id("jacoco")
    id("com.adarshr.test-logger").version("4.0.0")
}

group = "bna21u242"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.jetbrains:annotations:26.0.1")
    implementation("org.xerial:sqlite-jdbc:3.47.0.0")
    implementation("org.hibernate.orm:hibernate-core:6.6.2.Final")
    implementation("org.hibernate.orm:hibernate-community-dialects:6.6.2.Final")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.18.1")
}

jacoco {
    toolVersion = "0.8.12"
}

buildscript {
    repositories {
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
    }
    dependencies {
        classpath("com.adarshr:gradle-test-logger-plugin:4.0.0")
    }
}

apply(plugin = "com.adarshr.test-logger")

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required = false
        csv.required = false
        html.outputLocation = layout.buildDirectory.dir("jacocoHtml")
    }
}

tasks.shadowJar {
    manifest {
        attributes["Main-Class"] = "Main"
        attributes["Created-By"] = "Nikolay Baranov"
    }
    archiveBaseName.set("lab5")
    archiveClassifier.set("")
    archiveVersion.set("")
}