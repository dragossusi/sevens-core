plugins {
    kotlin("multiplatform")
    `maven-publish`
    signing
}

group = "ro.dragossusi.sevens"
version = Versions.app

kotlin {

    jvm()
    js(BOTH) {
        nodejs()
//        binaries.executable()
    }
    ios{
        binaries.framework {
            baseName = "SevensSocket"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.serialization}")
                implementation("ro.dragossusi.sevens:payload:${Versions.app}")
                implementation(project(":logger"))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val jvmMain by getting {}
        val jsMain by getting {}
    }
}

publishing {
    publications {
        publications.withType<MavenPublication> {
            pom {
                name.set("Sevens Socket")
                description.set("Socket classes used in sevens")
                url.set("http://www.dragossusi.ro/sevens")
            }
        }
    }
}

apply<PublishPlugin>()