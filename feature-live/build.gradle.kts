import io.getstream.Configuration

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
  id(libs.plugins.android.library.get().pluginId)
  id(libs.plugins.kotlin.android.get().pluginId)
  id(libs.plugins.kotlin.kapt.get().pluginId)
  id(libs.plugins.navigation.get().pluginId)
  id(libs.plugins.hilt.plugin.get().pluginId)
}

android {
  compileSdk = Configuration.compileSdk

  defaultConfig {
    minSdk = Configuration.minSdk
    targetSdk = Configuration.targetSdk
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
  implementation(project(":core-uicomponents"))

  // feature modules
  implementation(project(":feature-user"))
  implementation(project(":feature-home-common"))

  // data binding
  implementation(libs.bindables)

  // youtube player
  implementation(libs.youtube.player)

  // hilt
  implementation(libs.hilt.android)
  kapt(libs.hilt.compiler)
}