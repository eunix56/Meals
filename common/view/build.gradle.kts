plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-parcelize")
}

android {
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
    api(project(Dependencies.ProjectLib.domain))

    Dependencies.View.run {
        implementation(appCompat)
        implementation(constraintLayout)
        implementation(material)
        api(lottie)
        api(glide)
        annotationProcessor(Dependencies.View.AnnotationProcessor.glide)
    }

    Dependencies.AndroidX.run {
        implementation(Dependencies.AndroidX.navigationUiKtx)
        implementation(Dependencies.AndroidX.navigationFragmentKtx)
    }

    Dependencies.UnitTesting.run {
        testImplementation(junit)
    }

    Dependencies.AndroidTesting.run {
        androidTestImplementation(extJunit)
        androidTestImplementation(espressoCore)
    }
}
