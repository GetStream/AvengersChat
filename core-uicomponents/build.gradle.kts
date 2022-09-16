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
  // android supports
  implementation(libs.material)

  // stream chat UI components
  implementation(libs.stream.ui.components)

  // startup
  implementation(libs.androidx.startup)

  // image loading
  implementation(libs.coil)
  implementation(libs.coil.gif)

  // custom views
  api(libs.veil)
}