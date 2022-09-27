import io.getstream.Configuration

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
  id(libs.plugins.android.application.get().pluginId)
  id(libs.plugins.kotlin.android.get().pluginId)
  id(libs.plugins.kotlin.kapt.get().pluginId)
  id(libs.plugins.kotlin.parcelize.get().pluginId)
  id(libs.plugins.hilt.plugin.get().pluginId)
  id(libs.plugins.navigation.get().pluginId)
  id(libs.plugins.crashlyrics.get().pluginId)
  id(libs.plugins.googleService.get().pluginId)
  id(libs.plugins.ksp.get().pluginId) version libs.versions.ksp.get()
}

android {
  compileSdk = Configuration.compileSdk
  defaultConfig {
    applicationId = "io.getstream.avengerschat"
    minSdk = Configuration.minSdk
    targetSdk = Configuration.targetSdk
    versionCode = Configuration.versionCode
    versionName = Configuration.versionName
    vectorDrawables.useSupportLibrary = true
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }

  buildFeatures {
    dataBinding = true
  }

  hilt {
    enableAggregatingTask = true
  }

  lint {
    abortOnError = false
  }
    buildTypes {
        create("benchmark") {
            signingConfig = signingConfigs.getByName("debug")
            matchingFallbacks += listOf("release")
            isDebuggable = false
        }
    }
}

dependencies {
  // core modules
  implementation(project(":core-data"))
  implementation(project(":core-uicomponents"))

  // feature modules
  implementation(project(":feature-home"))
  implementation(project(":feature-user"))

  // androidx
  implementation(libs.androidx.lifecycle)
  implementation(libs.androidx.startup)

  // stream chat core + UI SDK
  implementation(libs.stream.ui.components)
  implementation(libs.stream.firebase)

  // data binding
  implementation(libs.bindables)

  // hilt
  implementation(libs.hilt.android)
  kapt(libs.hilt.compiler)

  // coroutines
  implementation(libs.coroutines)

  // bundler
  implementation(libs.bundler)

  // custom views
  implementation(libs.transformationLayout)
  implementation(libs.descretescroll)

  implementation(platform(libs.firebase.bom))
  implementation("com.google.firebase:firebase-crashlytics")
  implementation("com.google.firebase:firebase-messaging")
  implementation("com.google.firebase:firebase-analytics")

  // debugging
  implementation(libs.timber)
}
