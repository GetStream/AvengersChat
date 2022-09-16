import io.getstream.Configuration

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
  id(libs.plugins.android.library.get().pluginId)
  id(libs.plugins.kotlin.android.get().pluginId)
  id(libs.plugins.kotlin.kapt.get().pluginId)
}

android {
  compileSdk = Configuration.compileSdk

  defaultConfig {
    minSdk = Configuration.minSdk
    targetSdk = Configuration.targetSdk
  }
}

dependencies {
  api(project(":core-model"))
  implementation(project(":core-network"))
  implementation(project(":core-database"))

  // coroutines
  implementation(libs.coroutines)

  // network
  implementation(libs.sandwich)

  // di
  implementation(libs.hilt.android)
  kapt(libs.hilt.compiler)
}