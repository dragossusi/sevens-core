plugins {
    kotlin("multiplatform")// version "$kotlin_version"
    `maven-publish`
    signing
}

group = "ro.dragossusi.sevens"
version = Versions.app

kotlin {
    /* Targets configuration omitted. 
    *  To find out how to configure the targets, please follow the link:
    *  https://kotlinlang.org/docs/reference/building-mpp-with-gradle.html#setting-up-targets */
    jvm()
    js(IR) {
        nodejs()
        binaries.executable()
    }
//    ios() {
//        binaries {
//            framework {
//                baseName = "SevensGameListener"
//            }
//        }
//    }
//    linuxX64()
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