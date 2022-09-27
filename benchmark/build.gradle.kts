import io.getstream.Configuration

plugins {
  id("com.android.test")
  id("org.jetbrains.kotlin.android")
}

android {
  namespace = "io.getstream.avengerschat.benchmark"
  compileSdk = Configuration.compileSdk

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }

  defaultConfig {
    minSdk = 23
    targetSdk = Configuration.targetSdk
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildTypes {
    // This benchmark buildType is used for benchmarking, and should function like your
    // release build (for example, with minification on). It"s signed with a debug key
    // for easy local/CI testing.
    create("benchmark") {
      isDebuggable = true
      signingConfig = getByName("debug").signingConfig
      matchingFallbacks += listOf("release")
    }
  }

  targetProjectPath = ":app"
  experimentalProperties["android.experimental.self-instrumenting"] = true
}

dependencies {
  implementation(libs.android.test.runner)
  implementation(libs.uiautomator)
  implementation(libs.macrobenchmark)
  implementation(libs.profileinstaller)
}

androidComponents {
  beforeVariants(selector().all()) {
    it.enabled = it.buildType == "benchmark"
  }
}

