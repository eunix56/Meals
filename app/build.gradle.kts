plugins {
    id("com.android.application")
    id("kotlin-android")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
}

android {
    namespace = Dependencies.applicationId
    defaultConfig {
        applicationId = Dependencies.applicationId
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        compileSdk = AppConfig.compileSdk
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        testInstrumentationRunner = AppConfig.androidTestInstrumentation
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }

        getByName("debug") {
            isMinifyEnabled = false
        }
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    @Suppress("UnstableApiUsage")
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    @Suppress("UnstableApiUsage")
    viewBinding {
        android.buildFeatures.viewBinding = true
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(project(Dependencies.ProjectLib.domain))
    implementation(project(Dependencies.ProjectLib.remote))
    implementation(project(Dependencies.ProjectLib.data))
    implementation(project(Dependencies.ProjectLib.navigation))
    implementation(project(Dependencies.ProjectLib.home))
    implementation(project(Dependencies.ProjectLib.recipes))
    implementation(project(Dependencies.ProjectLib.favourites))

    Dependencies.View.run {
        implementation(appCompat)
        implementation(constraintLayout)
        implementation(material)
    }

    Dependencies.AndroidX.run {
        implementation(coreKtx)
        implementation(legacySupport)
        implementation(lifecycleRuntime)
        implementation(viewModel)
        implementation(navigationUiKtx)
        implementation(navigationFragmentKtx)
        implementation(roomRuntime)
        implementation(roomKtx)
        kapt(Dependencies.AndroidX.AnnotationProcessor.room)
    }

    Dependencies.DI.run {
        implementation(daggerHilt)
        kapt(Dependencies.DI.AnnotationProcessor.daggerHilt)
    }

    Dependencies.UnitTesting.run {
        testImplementation(junit)
    }

    Dependencies.AndroidTesting.run {
        androidTestImplementation(extJunit)
        androidTestImplementation(espressoCore)
    }
}