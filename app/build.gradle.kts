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
}

dependencies {
    // modules
    implementation(project(":core-model"))

    // android supports
    implementation(libs.material)

    // jetpack components
    implementation(libs.androidx.fragment)
    implementation(libs.androidx.lifecycle)
    implementation(libs.androidx.navigation.ktx)
    implementation(libs.androidx.navigation.ui)
    implementation(libs.androidx.navigation.dynamic)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)

    // stream chat core + UI SDK
    implementation(libs.stream.ui.components)
    implementation(libs.stream.firebase)

    // binding
    implementation(libs.bindables)

    // startup
    implementation(libs.androidx.startup)

    // hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    // network
    implementation(libs.sandwich)
    implementation(libs.retrofit)
    implementation(libs.retrofit.moshi)
    implementation(libs.okhttp.interceptor)

    // moshi
    implementation(libs.moshi)
    ksp(libs.moshi.codegen)

    // coroutines
    implementation(libs.coroutines)

    // image loading
    implementation(libs.coil)
    implementation(libs.coil.gif)

    // youtube player
    implementation(libs.youtube.player)

    // bundler
    implementation(libs.bundler)

    // custom views
    implementation(libs.transformationLayout)
    implementation(libs.veil)
    implementation(libs.descretescroll)

    implementation(platform(libs.firebase.bom))
    implementation("com.google.firebase:firebase-crashlytics")
    implementation("com.google.firebase:firebase-messaging")
    implementation("com.google.firebase:firebase-analytics")

    // debugging
    implementation(libs.timber)
}
