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
                        name.set("MIT License")
                        url.set("https://opensource.org/licenses/MIT")
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
}
