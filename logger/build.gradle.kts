plugins {
    kotlin("multiplatform") // version "$kotlin_version"
    kotlin("plugin.serialization")// version "$kotlin_version"
    `maven-publish`
    signing
}

group = "ro.dragossusi"
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
//                baseName = "SevensLogger"
//            }
//        }
//    }
//    linuxX64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.serialization}")
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

apply<PublishPlugin>()

publishing {
    publications {
        group = "ro.dragossusi"
        publications.withType<MavenPublication> {
            groupId = "ro.dragossusi"
            pom {
                name.set("Multiplatform logger")
                description.set("Logger classes")
                url.set("http://www.dragossusi.ro/logger")
            }
        }
    }
    repositories {
        maven {
            name = "sonatype"
            url = uri("https://oss.sonatype.org/service/local/staging/deploy/maven2/")
            credentials {
                username = project.property("sonatype.username").toString()
                password = project.property("sonatype.password").toString()
            }
        }
    }
}