buildscript {
    repositories {
        jcenter()
        mavenCentral()
    }
}

plugins {
    id("org.jetbrains.kotlin.multiplatform") version Versions.kotlin apply false
    id("org.jetbrains.kotlin.plugin.serialization") version Versions.kotlin apply false
    `maven-publish`
}

allprojects {
    repositories {
        mavenLocal()
        mavenCentral()
        jcenter()
    }
}
