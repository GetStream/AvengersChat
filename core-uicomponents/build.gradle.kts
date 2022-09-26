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

  buildFeatures {
    dataBinding = true
  }

  lint {
    abortOnError = false
  }
}

dependencies {
  // androidx
  api(libs.material)
  api(libs.androidx.fragment)

  // stream chat UI components
  api(libs.stream.ui.components)

  // startup
  implementation(libs.androidx.startup)

  // threeten backport
  implementation(libs.androidAbp)

  // image loading
  implementation(libs.coil)
  implementation(libs.coil.gif)

  // custom views
  api(libs.veil)
}