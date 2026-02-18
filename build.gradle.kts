plugins {
    kotlin("jvm") version "2.3.0"
    `java-library`
    `maven-publish`
    signing
}

group = "org.kindredhq.discussions"
version = "0.1.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

dependencies {
    api("com.benasher44:uuid:0.0.26")
    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(25)
}

java {
    withSourcesJar()
    withJavadocJar()
}

tasks.test {
    useJUnitPlatform()
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
