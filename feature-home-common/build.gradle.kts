@Suppress("DSL_SCOPE_VIOLATION")
plugins {
  id(libs.plugins.android.library.get().pluginId)
  id(libs.plugins.kotlin.android.get().pluginId)
  id(libs.plugins.kotlin.kapt.get().pluginId)
  id(libs.plugins.navigation.get().pluginId)
  id(libs.plugins.hilt.plugin.get().pluginId)
}

android {
  compileSdk = io.getstream.Configuration.compileSdk

  defaultConfig {
    minSdk = io.getstream.Configuration.minSdk
    targetSdk = io.getstream.Configuration.targetSdk
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }

  buildFeatures {
    dataBinding = true
  }
}

dependencies {
  // core modules
  implementation(project(":core-data"))

  // stream chat SDK
  implementation(libs.stream.offline)

  // data binding
  implementation(libs.bindables)

  // hilt
  implementation(libs.hilt.android)
  kapt(libs.hilt.compiler)
}