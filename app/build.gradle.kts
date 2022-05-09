plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
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

    viewBinding {
        android.buildFeatures.viewBinding = true
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(project(Dependencies.ProjectLib.domain))
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