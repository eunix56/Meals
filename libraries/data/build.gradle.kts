plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    namespace = "com.eunice.data"

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
}


dependencies {
    implementation(project(Dependencies.ProjectLib.domain))

    Dependencies.Network.run {
        implementation(moshi)
        implementation(retrofit)
        implementation(moshiKotlin)
        annotationProcessor(Dependencies.Network.AnnotationProcessor.moshi)
    }

    Dependencies.AndroidX.run {
        implementation(roomRuntime)
        implementation(roomKtx)
        kapt(Dependencies.AndroidX.AnnotationProcessor.room)
    }

    Dependencies.DI.run {
        implementation(daggerHilt)
        kapt(Dependencies.DI.AnnotationProcessor.daggerHilt)
    }

    Dependencies.Coroutines.run {
        implementation(android)
    }

    Dependencies.UnitTesting.run {
        testImplementation(junit)
    }

    Dependencies.AndroidTesting.run {
        androidTestImplementation(extJunit)
        androidTestImplementation(espressoCore)
    }
}