plugins {
    alias(libs.plugins.spotless)
}

buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    dependencies {
        classpath(libs.agp)
        classpath(libs.kotlin.gradlePlugin)
        classpath(libs.hilt.plugin)
        classpath(libs.navigation.plugin)
        classpath(libs.crashlyrics.plugin)
        classpath(libs.googleService.plugin)
    }
}
