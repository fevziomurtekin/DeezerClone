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
    val safeArgsPlugin              = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation_version}"
    val safeArgsApply               = "androidx.navigation.safeargs"

    /* Detekt */
    val detekt                      = "io.gitlab.arturbosch.detekt"
    val detektPlugin                = "io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${Versions.detekt_version}"

    val applicationId               = "com.fevziomurtekin.deezer"
    val min                         = 19
    val compile                     = 30
    val versionCode                 = 1
    val versionName                 = "1.0"
    val buildVersion                = "30.0.1"
    val target                      = compile
    val testInstrumentationRunner   = "com.fevziomurtekin.deezer.CustomTestRunner"
    val javaVersion                 = "1.8"
    val main                        = "main"
    val release                     = "release"
    val proguardTxt                 = "proguard-android.txt"
    val proguardPro                 = "proguard-rules.pro"


}