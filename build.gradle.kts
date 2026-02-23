import org.jetbrains.kotlin.gradle.internal.backend.common.serialization.metadata.DynamicTypeDeserializer.id

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.detekt)
    alias(libs.plugins.spotless)
    `java-library`
    `maven-publish`
    signing
}

group = "org.kindredhq.discussions"
version = "0.1.0"

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(25)
}

java {
    withSourcesJar()
    withJavadocJar()
}

detekt {
    buildUponDefaultConfig = true
    allRules = false
    config.setFrom(layout.projectDirectory.file("detekt.yml"))
}

spotless {
    kotlin {
        target("**/*.kt")
        ktlint("1.8.0")
        trimTrailingWhitespace()
        endWithNewline()
    }
}

tasks.test {
    useJUnitPlatform()
}

tasks.named("check") {
    dependsOn("detekt")
    dependsOn("spotlessCheck")
}

dependencies {

    implementation(libs.kotlinx.serialization)

    testImplementation(kotlin("test"))
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])

            pom {
                name.set("discussions-core")
                description.set("Threaded discussion domain models, DTOs, and contracts for The Kindred ecosystem.")
                url.set("https://github.com/kindredhq/discussions-core")

                licenses {
                    license {
                        name.set("Apache License, Version 2.0")
                        url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }

                developers {
                    developer {
                        id.set("barret")
                        name.set("Barret Vogtman")
                    }
                }

                scm {
                    connection.set("scm:git:git://github.com/kindredhq/discussions-core.git")
                    developerConnection.set("scm:git:ssh://github.com/kindredhq/discussions-core.git")
                    url.set("https://github.com/kindredhq/discussions-core")
                }
            }
        }
    }
    repositories {
        maven {
            name = "OSSRH"
            // The compatibility endpoint for the Staging API
            url = uri("https://ossrh-staging-api.central.sonatype.com/service/local/staging/deploy/maven2/")

            credentials {
                // These MUST be your Portal Token credentials, not your website password
                username = project.property("ossrhUsername").toString()
                password = project.property("ossrhPassword").toString()
            }
        }
    }
}

signing {
    val keyId = project.findProperty("signing.keyId") as? String
    val password = project.findProperty("signing.password") as? String
    val secretKeyRingFile = project.findProperty("signing.secretKeyRingFile") as? String

    if (keyId != null && password != null && secretKeyRingFile != null) {
        val keyFile = file(secretKeyRingFile)
        if (keyFile.exists()) {
            // This is the most reliable method for Gradle 9+
            useInMemoryPgpKeys(keyId, keyFile.readText(), password)
        } else {
            logger.warn("Signing key file not found at: ${keyFile.absolutePath}")
        }
    } else {
        logger.warn("Signing properties are missing. Check your global gradle.properties.")
    }

    sign(publishing.publications["mavenJava"])
}

tasks.register<Zip>("bundleRelease") {
    archiveFileName.set("discussioncore.zip")
    destinationDirectory.set(layout.buildDirectory.dir("distributions"))

    // This creates the 'org/kindredhq/...' folder structure inside the ZIP
    from("C:/Users/bchat/.m2/repository/org/kindredhq/discussions/discussions-core/0.1.0") {
        into("org/kindredhq/discussions/discussions-core/0.1.0")
    }
}
