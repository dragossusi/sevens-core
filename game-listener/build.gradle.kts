plugins {
    kotlin("multiplatform")// version "$kotlin_version"
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
    ios {
        binaries {
            framework {
                baseName = "SevensGameListener"
            }
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("ro.dragossusi.sevens:payload:${Versions.app}")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
    }
}

publishing {
    publications {
        publications.withType<MavenPublication> {
            pom {
                name.set("Sevens game listener")
                description.set("Game listeners used in sevens")
                url.set("http://www.dragossusi.ro/sevens")
            }
        }
    }
}

apply<PublishPlugin>()