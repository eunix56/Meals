plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.eunice.home"

    defaultConfig {
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        compileSdk = AppConfig.compileSdk

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
    implementation(project(Dependencies.ProjectLib.commonView))
    implementation(project(Dependencies.ProjectLib.commonUtils))

    Dependencies.View.run {
        implementation(appCompat)
        implementation(constraintLayout)
        implementation(material)
    }

    Dependencies.DI.run {
        implementation(daggerHilt)
        kapt(Dependencies.DI.AnnotationProcessor.daggerHilt)
    }

    Dependencies.AndroidX.run {
        implementation(coreKtx)
        implementation(legacySupport)
        implementation(viewModel)
        implementation(lifecycleRuntime)
        implementation(navigationUiKtx)
        implementation(navigationFragmentKtx)
    }

    Dependencies.UnitTesting.run {
        testImplementation(junit)
    }

    Dependencies.AndroidTesting.run {
        androidTestImplementation(extJunit)
        androidTestImplementation(espressoCore)
    }
}