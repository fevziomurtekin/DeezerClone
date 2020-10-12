object Dependencies {

    /* androidX & material design */
    val appcompat           = "androidx.appcompat:appcompat:${Versions.appcompat_version}"
    val constraint_layout   = "androidx.constraintlayout:constraintlayout:${Versions.constraint_layout_version}"
    val vector_drawable     = "androidx.vectordrawable:vectordrawable:${Versions.vector_drawable_version}"
    val material_design     = "com.google.android.material:material:${Versions.material_version}"
    val ktxLib              = "androidx.core:core-ktx:${Versions.ktxVersion}"

    /* ViewModel & LiveData  */
    val lifecycle           = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycleVersion}"
    val lifecycle_compiler  = "android.arch.lifecycle:compiler:${Versions.androidArchitectureVersion}"
    val lifecycle_test      = "android.arch.core:core-testing:${Versions.androidArchitectureVersion}"
    val lifecycle_runtime   = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleVersion}"
    val lifecycle_livedata  = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycleVersion}"
    val viewmodel           = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleVersion}"
    val viewmodel_savedState= "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.lifecycleVersion}"
    val lifecycle_kapt      = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycleVersion}"
    val lifecycle_fragment  = "androidx.fragment:fragment-ktx:${Versions.fragmentVersion}"

    /* Recyclerview */
    val recyclerview        = "androidx.recyclerview:recyclerview:${Versions.recyclerview_version}"

    /* Retrofit */
    val okhttp              = "com.squareup.okhttp3:okhttp:${Versions.okhttp3_version}"
    val okhttp_logging      = "com.squareup.okhttp3:logging-interceptor:${Versions.logging_version}"
    val retrofit            = "com.squareup.retrofit2:retrofit:${Versions.retrofit2_version}"
    val retrofit_convertor  = "com.squareup.retrofit2:converter-gson:${Versions.retrofit2_converter_version}"
    val retrofit_adapter    = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit2_version}"

    /* kotlin serialization */
    val kotlin_serialization            = "org.jetbrains.kotlinx:kotlinx-serialization-core:${Versions.serialization_version}"
    val kotlin_serialization_converter  = "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${Versions.serialization_converter_version}"

    /* Room */
    val room                = "androidx.room:room-runtime:${Versions.room_version}"
    val room_ktx            = "androidx.room:room-ktx:${Versions.room_version}"
    val room_compiler       = "androidx.room:room-compiler:${Versions.room_version}"
    val room_testing        = "androidx.room:room-testing:${Versions.room_version}"

    /* Timber */
    val timber              = "com.jakewharton.timber:timber:${Versions.timber_version}"

    /*Android Hilt && Dagger */
    val hilt                = "com.google.dagger:hilt-android:${Versions.hilt_version}"
    val hilt_kapt           = "com.google.dagger:hilt-android-compiler:${Versions.hilt_version}"
    val hilt_androidx       = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.a_hilt_version}"
    val hilt_androidx_kapt  = "androidx.hilt:hilt-compiler:${Versions.a_hilt_version}"
    val hilt_common         = "androidx.hilt:hilt-common:${Versions.hilt_version}"
    val hilt_test           = "com.google.dagger:hilt-android-testing:${Versions.hilt_version}"

    /* Android Test */
    val test_orch           = "androidx.test:orchestrator:${Versions.test_rule_version}"
    val test_core           = "androidx.test:core:${Versions.test_core_version}"
    val test_rules          = "androidx.test:rules:${Versions.test_rule_version}"
    val test_runner         = "androidx.test:runner:${Versions.test_runner_version}"
    val espresso            = "androidx.test.espresso:espresso-core:${Versions.espresso_version}"
    val testTruth           = "androidx.test.ext:truth:${Versions.truth_version}"
    val testExt             = "androidx.test.ext:junit-ktx:${Versions.test_rule_version}"
    val mockK               = "io.mockk:mockk:${Versions.mockK_version}"
    val coreTesting         = "androidx.arch.core:core-testing:${Versions.core_testing_version}"
    val jUnit               = "junit:junit:${Versions.junit_version}"
    val robolectric         = "org.robolectric:robolectric:${Versions.robolectric_version}"
    val mockServer          = "com.squareup.okhttp3:mockwebserver:${Versions.okhttp3_version}"
    val mockitoKotlin       = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockito_version}"
    val turbine             = "app.cash.turbine:turbine:${Versions.turbine_version}"

    /* Navigation */
    val navigation_fragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation_version}"
    val navigation_ui       = "androidx.navigation:navigation-ui-ktx:${Versions.navigation_version}"

    /* Pagging */
    val pagging             = "android.arch.paging:runtime:${Versions.pagging_version}"

    /* Other libs */
    val circularImageLib    = "de.hdodenhof:circleimageview:${Versions.circular_imageview_version}"

    /* Coil */
    val coilLib             = "io.coil-kt:coil:${Versions.coil_version}"

    /* Coroutines */
    val coroutines_test     = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines_android_version}"
    val coroutines_android  = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines_android_version}"
    val coroutines_core_lib = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines_android_version}"
    val coroutines_adapter  = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.coroutines_adapter_version}"

    val multidex_lib        = "com.android.support:multidex:${Versions.multidexVersion}"

    /* Shimmer Layout */
    val shimmerLib          = "com.facebook.shimmer:shimmer:${Versions.shimmer_version}"

    /* Exo Player */
    val exoplayer_core_lib  = "com.google.android.exoplayer:exoplayer-core:${Versions.exoplayer_version}"
    val exoplayer_dash_lib  = "com.google.android.exoplayer:exoplayer-dash:${Versions.exoplayer_version}"
    val exoplayer_ui_lib    = "com.google.android.exoplayer:exoplayer-ui:${Versions.exoplayer_version}"

}