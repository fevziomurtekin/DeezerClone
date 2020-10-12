plugins{
    id(Plugins.androidApplication)
    id(Plugins.hiltPlugin)
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
    }
    buildTypes {
        getByName(Plugins.debug) {
            signingConfig= signingConfigs.getByName(Plugins.debug)
            isMinifyEnabled = true
            isShrinkResources = false
            isDebuggable = true
            isTestCoverageEnabled = true
            proguardFiles(getDefaultProguardFile(Plugins.proguardTxt), Plugins.proguardPro)
        }
    }
    packagingOptions{
        exclude(Plugins.metaDependecies)
        exclude(Plugins.metaLicense)
        exclude(Plugins.metaIndex)
    }
    compileOptions{
        setSourceCompatibility(Plugins.javaVersion)
        setTargetCompatibility(Plugins.javaVersion)
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
    }

    testOptions {
        execution = Plugins.testOptions
    }

    buildFeatures {
        dataBinding=true
    }

    dexOptions{
        javaMaxHeapSize = "4g"
        preDexLibraries = false
    }


}

dependencies{
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(kotlin("stdlib-jdk7", Versions.kotlinVersion))
    implementation(Dependencies.ktxLib)
    /** androidx & material design **/
    implementation(Dependencies.appcompat)
    implementation(Dependencies.constraint_layout)
    implementation(Dependencies.vector_drawable)
    implementation(Dependencies.material_design)
    /** viewmodel & livedata **/
    implementation(Dependencies.lifecycle)
    kapt(Dependencies.lifecycle_compiler)
    implementation(Dependencies.lifecycle_test)
    implementation(Dependencies.viewmodel)
    implementation(Dependencies.lifecycle_livedata)
    implementation(Dependencies.viewmodel_savedState)
    implementation(Dependencies.lifecycle_runtime)
    implementation(Dependencies.lifecycle_fragment)
    kapt(Dependencies.lifecycle_kapt)
    /** recyclerview **/
    implementation(Dependencies.recyclerview)
    /** retrofit **/
    implementation(Dependencies.okhttp)
    implementation(Dependencies.okhttp_logging)
    implementation(Dependencies.retrofit)
    implementation(Dependencies.retrofit_convertor)
    implementation(Dependencies.retrofit_adapter)
    /** room **/
    implementation(Dependencies.room)
    implementation(Dependencies.room_ktx)
    kapt(Dependencies.room_compiler)
    testImplementation(Dependencies.room_testing)
    /** timber **/
    implementation(Dependencies.timber)
    /** hilt **/
    implementation(Dependencies.hilt)
    implementation(Dependencies.hilt_androidx)
    kapt(Dependencies.hilt_kapt)
    kapt(Dependencies.hilt_androidx_kapt)
    //kaptAndroidTest(Dependencies.hilt_androidx_kapt)
    /** navigation **/
    implementation(Dependencies.navigation_fragment)
    implementation(Dependencies.navigation_ui)
    /** pagging **/
    implementation(Dependencies.pagging)
    /** Other libs **/
    implementation(Dependencies.circularImageLib)
    /** Coroutines **/
    implementation(Dependencies.coroutines_android)
    implementation(Dependencies.coroutines_core_lib)
    implementation(Dependencies.coroutines_adapter)
    /** MultiDex **/
    implementation(Dependencies.multidex_lib)
    /** kotlin serialization **/
    implementation(Dependencies.kotlin_serialization_converter)
    implementation(Dependencies.kotlin_serialization)
    /** coil **/
    implementation(Dependencies.coilLib)
    /** Shimmer layout */
    implementation(Dependencies.shimmerLib)

    /** android test library  */
    androidTestUtil(Dependencies.test_orch)
    androidTestImplementation(Dependencies.hilt_test)
    androidTestImplementation(Dependencies.test_runner)
    androidTestImplementation(Dependencies.test_rules)
    androidTestImplementation(Dependencies.test_core)
    androidTestImplementation(Dependencies.espresso)
    testImplementation(Dependencies.testTruth)
    //androidTestImplementation(Dependencies.testTruth)
    androidTestImplementation(Dependencies.testExt)
    testImplementation(Dependencies.mockK)
    testImplementation(Dependencies.coreTesting)
    testImplementation(Dependencies.jUnit)
    testImplementation(Dependencies.mockServer)
    testImplementation(Dependencies.coroutines_test)
    testImplementation(Dependencies.robolectric)
    androidTestImplementation(Dependencies.robolectric)
    testImplementation(Dependencies.mockitoKotlin)
    testImplementation(Dependencies.turbine)

    /** exoplayer */
    implementation(Dependencies.exoplayer_core_lib)
    implementation(Dependencies.exoplayer_dash_lib)
    implementation(Dependencies.exoplayer_ui_lib)
}
