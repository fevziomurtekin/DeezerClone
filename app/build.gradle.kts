import Libs.Coroutines.android

plugins{
    id(Plugins.androidApplication)
    id(Plugins.hiltPlugin)
    id(Plugins.safeArgsApply)
    kotlin(Plugins.kotlinAndroid)
    kotlin(Plugins.kotlinAndroidExtensions)
    kotlin(Plugins.kotlinKaptExtensions)
    kotlin(Plugins.kotlinSerialization).version("1.4.10")
}

android{
    compileSdkVersion(Plugins.compile)
    buildToolsVersion(Plugins.buildVersion)
    defaultConfig{
        applicationId = Plugins.applicationId
        minSdkVersion(Plugins.min)
        targetSdkVersion(Plugins.target)
        versionCode = Plugins.versionCode
        versionName = Plugins.versionName
        multiDexEnabled = true
        testInstrumentationRunner = Plugins.testInstrumentationRunner
        vectorDrawables.useSupportLibrary = true
        testInstrumentationRunnerArguments(mutableMapOf("clearPackageData" to "true"))

    }
    buildTypes {
        getByName(Plugins.release) {
           isMinifyEnabled = false
           isShrinkResources = false
           isDebuggable = true
           isTestCoverageEnabled = true
           proguardFiles(getDefaultProguardFile(Plugins.proguardTxt), Plugins.proguardPro)
        }
    }
    compileOptions{
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8

    }
    kotlinOptions {
        jvmTarget = Plugins.javaVersion
    }
    lintOptions{
        isCheckReleaseBuilds = false
        isAbortOnError = false
    }
    androidExtensions { isExperimental = true }
    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin")
        getByName("androidTest").java.srcDirs("src/androidTest/jaa")
        getByName("test").java.srcDirs("src/sharedTest")
        getByName("androidTest").java.srcDirs("src/sharedTest")

    }
    buildFeatures {
        dataBinding=true
    }
    dexOptions{
        javaMaxHeapSize = "4g"
        preDexLibraries = false
    }
    packagingOptions {
        exclude("META-INF/DEPENDENCIES")
        exclude("META-INF/LICENSE")
        exclude("META-INF/LICENSE.txt")
        exclude("META-INF/license.txt")
        exclude("META-INF/NOTICE")
        exclude("META-INF/NOTICE.txt")
        exclude("META-INF/notice.txt")
        exclude("META-INF/ASL2.0")
        exclude("META-INF/*.kotlin_module")
        exclude("META-INF/AL2.0")
        exclude("META-INF/LGPL2.1")

    }
}

dependencies{
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(kotlin("stdlib-jdk7", Versions.kotlinVersion))

    /** androidx & material design **/
    implementation(Libs.AndroidX.ktxLib)
    implementation(Libs.AndroidX.appcompat)
    implementation(Libs.AndroidX.constraint_layout)
    implementation(Libs.AndroidX.vector_drawable)
    implementation(Libs.AndroidX.material_design)
    implementation(Libs.AndroidX.recyclerview)

    /** viewmodel & livedata **/
    implementation(Libs.Jetpack.lifecycle)
    kapt(Libs.Jetpack.lifecycle_compiler)
    implementation(Libs.Jetpack.lifecycle_test)
    implementation(Libs.Jetpack.viewmodel)
    implementation(Libs.Jetpack.lifecycle_livedata)
    implementation(Libs.Jetpack.viewmodel_savedState)
    implementation(Libs.Jetpack.lifecycle_runtime)
    implementation(Libs.Jetpack.lifecycle_fragment)
    kapt(Libs.Jetpack.lifecycle_kapt)
    /** navigation **/
    implementation(Libs.Jetpack.navigation_fragment)
    implementation(Libs.Jetpack.navigation_ui)

    /** network **/
    implementation(Libs.Network.okhttp)
    implementation(Libs.Network.okhttp_logging)
    implementation(Libs.Network.retrofit)
    implementation(Libs.Network.retrofit_converter)
    implementation(Libs.Network.retrofit_adapter)
    /** room **/
    implementation(Libs.Local.room)
    implementation(Libs.Local.room_ktx)
    kapt(Libs.Local.room_compiler)
    testImplementation(Libs.Local.room_testing)

    /** hilt **/
    implementation(Libs.DI.hilt)
    implementation(Libs.DI.hilt_androidx)
    kapt(Libs.DI.hilt_kapt)
    kapt(Libs.DI.hilt_androidx_kapt)
    //kaptAndroidTest(Dependencies.hilt_androidx_kapt)

    /** Coroutines **/
    implementation(Libs.Coroutines.android)
    implementation(Libs.Coroutines.core)

    /** pagging **/
    implementation(Libs.Others.pagging)
    /** Other libs **/
    implementation(Libs.Others.circularImageLib)
    /** MultiDex **/
    implementation(Libs.Others.multidex_lib)
    /** coil **/
    implementation(Libs.Others.coilLib)
    /** Shimmer layout */
    implementation(Libs.Others.shimmerLib)
    /** timber **/
    implementation(Libs.Others.timber)

    /** android test library  */
    androidTestUtil(Libs.Test.test_orch)
    androidTestImplementation(Libs.DI.hilt_test)
    androidTestImplementation(Libs.Test.test_runner)
    androidTestImplementation(Libs.Test.test_rules)
    androidTestImplementation(Libs.Test.test_core)
    androidTestImplementation(Libs.Test.espresso)
    androidTestImplementation(Libs.Coroutines.test)
    androidTestImplementation(Libs.Test.espresso_contrib)
    androidTestImplementation(Libs.Jetpack.navigation_testing)
    androidTestImplementation(Libs.Test.mockK)
    kaptAndroidTest(Libs.DI.hilt_compiler)
    testImplementation(Libs.Test.testTruth)
    androidTestImplementation(Libs.Test.testTruth)
    androidTestImplementation(Libs.Test.testExt)
    debugImplementation(Libs.Test.fragment_test)
    testImplementation(Libs.Test.mockK)
    testImplementation(Libs.Test.coreTesting)
    testImplementation(Libs.Test.jUnit)
    testImplementation(Libs.Test.mockServer)
    testImplementation(Libs.Coroutines.test)
    testImplementation(Libs.Test.robolectric)
    testImplementation(Libs.Test.mockitoKotlin)
    testImplementation(Libs.Test.turbine)

    /** exoplayer */
    implementation(Libs.ExoPlayer.core)
    implementation(Libs.ExoPlayer.dash)
    implementation(Libs.ExoPlayer.ui)
}
