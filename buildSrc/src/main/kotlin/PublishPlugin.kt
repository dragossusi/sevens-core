import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.publish.maven.plugins.MavenPublishPlugin
import org.gradle.api.tasks.bundling.Jar
import org.gradle.kotlin.dsl.*
import org.gradle.plugins.signing.SigningPlugin

/**
 * SevensGame
 *
 * Copyright (C) 2020  Rachieru Dragos-Mihai
 *
 * SevensGame is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 *
 * SevensGame is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with SevensGame.  If not, see [License](http://www.gnu.org/licenses/) .
 *
 */
class PublishPlugin : Plugin<Project> {

    override fun apply(target: Project) = target.run {
        apply<MavenPublishPlugin>()
        apply<SigningPlugin>()

        version = Versions.app
        group = Details.groupId


        val emptyJavadocJar by tasks.registering(Jar::class) {
            archiveClassifier.set("javadoc")
        }

        publishing {
            publications {
                publications.withType<MavenPublication> {
                    setupPublication(target)
                }

                publications.withType<MavenPublication>().all {
                    artifact(emptyJavadocJar.get())
                }
            }
            setupRepos(project)
            setupSigning()
        }
    }

    private fun MavenPublication.setupPublication(target: Project) {

//        artifactId = target.name
        version = Versions.app
        groupId = Details.groupId

        pom {
            licenses {
                license {
                    name.set("The Apache License, Version 2.0")
                    url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                }
            }
            developers {
                developer {
                    id.set("dragossusi")
                    name.set("Dragos Rachieru")
                    email.set("rachierudragos97@gmail.com")
                }
            }
            val scmName = target.property("SCM_NAME")
            scm {
                connection.set("scm:git:git://github.com/dragossusi/$scmName.git")
                developerConnection.set("scm:git:ssh://github.com/dragossusi/$scmName.git")
                url.set("https://github.com/dragossusi/$scmName/")
            }
        }
    }

    private fun PublishingExtension.setupRepos(project: Project) {
        repositories {
            maven {
                name = "sonatype"
                url = project.uri("https://oss.sonatype.org/service/local/staging/deploy/maven2/")
                credentials {
                    username = project.property("sonatype.username").toString()
                    password = project.property("sonatype.password").toString()
                }
            }
        }
    }

    private fun Project.setupSigning() {
        signing {
            sign(publishing.publications)
        }
    }

}