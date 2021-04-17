object Libs {

    /* androidX & material design */
    object AndroidX {
        val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat_version}"
        val constraint_layout =
            "androidx.constraintlayout:constraintlayout:${Versions.constraint_layout_version}"
        val vector_drawable =
            "androidx.vectordrawable:vectordrawable:${Versions.vector_drawable_version}"
        val material_design = "com.google.android.material:material:${Versions.material_version}"
        val ktxLib = "androidx.core:core-ktx:${Versions.ktxVersion}"
        /* Recyclerview */
        val recyclerview        = "androidx.recyclerview:recyclerview:${Versions.recyclerview_version}"
    }

    /* ViewModel & LiveData  */
    object Jetpack {
        val lifecycle = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycleVersion}"
        val lifecycle_compiler =
            "android.arch.lifecycle:compiler:${Versions.androidArchitectureVersion}"
        val lifecycle_test = "android.arch.core:core-testing:${Versions.androidArchitectureVersion}"
        val lifecycle_runtime =
            "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleVersion}"
        val lifecycle_livedata =
            "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycleVersion}"
        val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleVersion}"
        val viewmodel_savedState =
            "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.lifecycleVersion}"
        val lifecycle_kapt = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycleVersion}"
        val lifecycle_fragment = "androidx.fragment:fragment-ktx:${Versions.fragmentVersion}"
        /* Navigation */
        val navigation_fragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation_version}"
        val navigation_ui       = "androidx.navigation:navigation-ui-ktx:${Versions.navigation_version}"
        val navigation_testing = "androidx.navigation:navigation-testing:${Versions.navigation_version}"

    }

    /* Retrofit */
    object Network {
        val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp3_version}"
        val okhttp_logging = "com.squareup.okhttp3:logging-interceptor:${Versions.logging_version}"
        val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit2_version}"
        val retrofit_converter =
            "com.squareup.retrofit2:converter-gson:${Versions.retrofit2_converter_version}"
        val retrofit_adapter =
            "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit2_version}"
    }

    /* Room */
    object Local {
        val room = "androidx.room:room-runtime:${Versions.room_version}"
        val room_ktx = "androidx.room:room-ktx:${Versions.room_version}"
        val room_compiler = "androidx.room:room-compiler:${Versions.room_version}"
        val room_testing = "androidx.room:room-testing:${Versions.room_version}"
    }

    /*Android Hilt && Dagger */
    object DI {
        val hilt = "com.google.dagger:hilt-android:${Versions.hilt_version}"
        val hilt_kapt = "com.google.dagger:hilt-android-compiler:${Versions.hilt_version}"
        val hilt_androidx = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.a_hilt_version}"
        val hilt_androidx_kapt = "androidx.hilt:hilt-compiler:${Versions.a_hilt_version}"
        val hilt_common = "androidx.hilt:hilt-common:${Versions.hilt_version}"
        val hilt_test = "com.google.dagger:hilt-android-testing:${Versions.hilt_version}"
        val hilt_compiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt_test_version}"
    }

    /* Android Test */
    object Test {
        val test_orch = "androidx.test:orchestrator:${Versions.test_rule_version}"
        val test_core = "androidx.test:core:${Versions.test_core_version}"
        val test_rules = "androidx.test:rules:${Versions.test_rule_version}"
        val test_runner = "androidx.test:runner:${Versions.test_rule_version}"
        val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso_version}"
        val espresso_contrib = "androidx.test.espresso:espresso-contrib:${Versions.espresso_version}"
        val testTruth = "androidx.test.ext:truth:${Versions.truth_version}"
        val testExt = "androidx.test.ext:junit:${Versions.test_ktx_version}"
        val fragment_test = "androidx.fragment:fragment-testing:${Versions.fragment_test_version}"
        val mockK = "io.mockk:mockk:${Versions.mockK_version}"
        val coreTesting = "androidx.arch.core:core-testing:${Versions.core_testing_version}"
        val jUnit = "junit:junit:${Versions.junit_version}"
        val robolectric = "org.robolectric:robolectric:${Versions.robolectric_version}"
        val mockServer = "com.squareup.okhttp3:mockwebserver:${Versions.okhttp3_version}"
        val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockito_version}"
        val turbine = "app.cash.turbine:turbine:${Versions.turbine_version}"
    }

    object Others {
        /* Timber */
        val timber              = "com.jakewharton.timber:timber:${Versions.timber_version}"
        /* Pagging */
        val pagging = "android.arch.paging:runtime:${Versions.pagging_version}"
        /* Other libs */
        val circularImageLib = "de.hdodenhof:circleimageview:${Versions.circular_imageview_version}"
        /* Coil */
        val coilLib = "io.coil-kt:coil:${Versions.coil_version}"
        val multidex_lib        = "com.android.support:multidex:${Versions.multidexVersion}"
        /* Shimmer Layout */
        val shimmerLib          = "com.facebook.shimmer:shimmer:${Versions.shimmer_version}"

    }
    /* Coroutines */
    object Coroutines {
        val test =
            "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines_android_version}"
        val android =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines_android_version}"
        val core =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines_android_version}"
    }

    /* Exo Player */
    object ExoPlayer {
        val core =
            "com.google.android.exoplayer:exoplayer-core:${Versions.exoplayer_version}"
        val dash =
            "com.google.android.exoplayer:exoplayer-dash:${Versions.exoplayer_version}"
        val ui =
            "com.google.android.exoplayer:exoplayer-ui:${Versions.exoplayer_version}"
    }

}