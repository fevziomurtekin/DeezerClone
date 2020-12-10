object Plugins{
    val androidGradlePlugin         = "com.android.tools.build:gradle:${Versions.buildToolVersion}"
    val googleVersionGradlePlugin   = "com.google.gms:google-services:${Versions.googleServiceVersion}"
    val kotlinGradlePlugin          = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"
    val mavenGradlePlugin           = "com.github.dcendents:android-maven-gradle-plugin:${Versions.mavenGradleVersion}"
    val jitpackUrl                  = "https://jitpack.io"
    val androidApplication          = "com.android.application"
    val kotlinAndroid               = "android"
    val kotlinAndroidExtensions     = "android.extensions"
    val androidLibrary              = "com.android.library"
    val kotlinKaptExtensions        = "kapt"
    val kotlinSerialization         = "plugin.serialization"
    val hiltExtensions              = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt_version}"
    val hiltPlugin                  = "dagger.hilt.android.plugin"
    /* Detekt */
    val detekt                      = "io.gitlab.arturbosch.detekt"
    val detektPlugins               = "io.gitlab.arturbosch.detekt:detekt-formatting:${Versions.detekt_version}"

    val applicationId               = "com.fevziomurtekin.deezer"
    val min                         = 19
    val compile                     = 30
    val versionCode                 = 1
    val versionName                 = "1.0"
    val buildVersion                = "30.0.1"
    val target                      = compile
    val testInstrumentationRunner   = "com.fevziomurtekin.deezer.AppTestRunner"
    val javaVersion                 = "1.8"
    val main                        = "main"
    val release                     = "release"
    val debug                       = "debug"
    val proguardTxt                 = "proguard-android.txt"
    val proguardPro                 = "proguard-rules.pro"
    val androidlibrary              = "com.android.library"
    val navSafePlugins              = "androidx.navigation.safeargs.kotlin"
    /** PACKAGING OPTIONS */
    val metaLicense                 = "META-INF/LICENSE"
    val metaDependecies             = "META-INF/DEPENDENCIES"
    val metaIndex                   = "META-INF/INDEX.LIST"

    val testOptions                 = "ANDROIDX_TEST_ORCHESTRATOR"

}