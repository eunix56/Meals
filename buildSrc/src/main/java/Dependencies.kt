import org.gradle.api.artifacts.dsl.DependencyHandler
/**
 * Created by {EUNICE BAKARE T.} on {5/6/22}
 * Email: {eunice@reach.africa}
 */

object Dependencies {
    const val applicationId = "com.example.meals"

    interface Libraries {
        val libraries: List<String>
    }

    object AndroidX: Libraries {
        val coreKtx = "androidx.core:core-ktx:${Version.coreKtx}"
        val legacySupport = "androidx.legacy:legacy-support-v4:${Version.legacySupport}"
        val navigationFragmentKtx: String =
            "androidx.navigation:navigation-fragment-ktx:${Version.navigation}"
        val navigationUiKtx: String =
            "androidx.navigation:navigation-ui-ktx:${Version.navigation}"
        val navigationSafeArgsKtx: String =
            "androidx.navigation:navigation-safe-args-gradle-plugin:${Version.navigation}"
        val viewModel: String =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.lifecycleKtx}"
        val lifecycleRuntime: String =
            "androidx.lifecycle:lifecycle-runtime-ktx:${Version.lifecycleKtx}"

        override val libraries: List<String>
            get() = listOf(
                coreKtx,
                legacySupport,
                navigationFragmentKtx,
                navigationUiKtx,
                viewModel,
                lifecycleRuntime
            )
    }

    object View: Libraries {
        val appCompat = "androidx.appcompat:appcompat:${Version.appCompat}"
        val material = "com.google.android.material:material:${Version.material}"
        val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Version.constraintlayout}"
        val lottie = "com.airbnb.android:lottie:${Version.lottie}"
        val glide = "com.github.bumptech.glide:glide:${Version.glide}"

        object AnnotationProcessor {
            val glide: String = "com.github.bumptech.glide:compiler:${Version.glide}"
        }

        override val libraries: List<String>
            get() = listOf(
                appCompat,
                material,
                constraintLayout
            )
    }

    object Network: Libraries {
        val moshi = "com.squareup.moshi:moshi:${Version.moshi}"
        val moshiKotlin = "com.squareup.moshi:moshi-kotlin:${Version.moshi}"
        val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofit}"
        val retrofitMoshi = "com.squareup.retrofit2:converter-moshi:${Version.retrofit}"
        val okHttp = "com.squareup.okhttp3:okhttp:${Version.okHttp}"
        val okHttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Version.okHttp}"

        object AnnotationProcessor {
            val moshi: String = "com.squareup.moshi:moshi-kotlin-codegen:${Version.moshi}"
        }

        override val libraries: List<String>
            get() = listOf(
                moshi,
                retrofit,
                retrofitMoshi,
                okHttp,
                okHttpLoggingInterceptor
            )
    }

    object DI: Libraries {
        val daggerHilt = "com.google.dagger:hilt-android:${Version.hilt}"

        object AnnotationProcessor {
            val daggerHilt = "com.google.dagger:hilt-android-compiler:${Version.hilt}"
        }

        override val libraries: List<String>
            get() = listOf(
                daggerHilt
            )
    }

    object Coroutines: Libraries {
        val core =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.kotlinxCoroutines}"
        val android =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.kotlinxCoroutines}"

        override val libraries: List<String>
            get() = listOf(
                android
            )
    }

    object UnitTesting: Libraries {
        val junit = "junit:junit:${Version.junit}"

        override val libraries: List<String>
            get() = listOf(
                junit
            )
    }

    object AndroidTesting: Libraries {
        val extJunit = "androidx.test.ext:junit:${Version.extJunit}"
        val espressoCore = "androidx.test.espresso:espresso-core:${Version.espresso}"

        override val libraries: List<String>
            get() = listOf(
                extJunit,
                espressoCore
            )
    }



    fun DependencyHandler.kapt(list: List<String>) {
        list.forEach { dependency ->
            add("kapt", dependency)
        }
    }

    fun DependencyHandler.implementation(list: List<String>) {
        list.forEach { dependency ->
            add("implementation", dependency)
        }
    }

    fun DependencyHandler.api(list: List<String>) {
        list.forEach { dependency ->
            add("api", dependency)
        }
    }

    fun DependencyHandler.androidTestImplementation(list: List<String>) {
        list.forEach { dependency ->
            add("androidTestImplementation", dependency)
        }
    }

    fun DependencyHandler.testImplementation(list: List<String>) {
        list.forEach { dependency ->
            add("testImplementation", dependency)
        }
    }

    object ProjectLib {
        const val app: String = ":app"
        const val commonUtils: String = ":common:utils"
        const val commonView: String = ":common:view"
        const val domain: String = ":libraries:domain"
        const val data: String = ":libraries:data"
        const val navigation: String = ":libraries:navigation"
        const val remote: String = ":libraries:remote"
        const val recipes: String = ":features:recipes"
        const val ingredients: String = ":features:ingredients"
        const val home: String = ":features:home"
        const val favourites: String = ":features:favourites"
    }

}