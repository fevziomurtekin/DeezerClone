import Plugins.detektPlugins

buildscript {
    val kotlin_version by extra("1.4.0")
    repositories{
        google()
        jcenter()
        maven {
            url = uri("https://oss.sonatype.org/content/repositories/snapshots/")
        }
        // maven(url = Plugins.fabricMavenUrl)
    }

    dependencies {
        classpath(Plugins.androidGradlePlugin)
        classpath(Plugins.googleVersionGradlePlugin)
        classpath(Plugins.kotlinGradlePlugin)
        classpath(Plugins.mavenGradlePlugin)
        classpath(Plugins.hiltExtensions)
        "classpath"("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
    }
}

plugins {
    id(Plugins.detekt).version(Versions.detekt_version)
}

dependencies {
    detektPlugins(Plugins.detektPlugins)
}


allprojects{
    repositories{
        google()
        jcenter()
        maven(url = Plugins.jitpackUrl)
    }
}

task("clean"){
    delete(rootProject.buildDir)
}


val detektAll by tasks.registering(io.gitlab.arturbosch.detekt.Detekt::class) {
    description = "Runs over whole code base without the starting overhead for each module."
    buildUponDefaultConfig = true
    autoCorrect = true
    parallel = true
    setSource(files(projectDir))
    config.setFrom(files("$rootDir/config/detekt.yml"))
    include("**/*.kt")
    include("**/*.kts")
    exclude("**/build/**")
    exclude("**/buildSrc/**")
    exclude("**/test/**/*.kt")
    reports {
        xml.enabled = false
        html.enabled = false
        txt.enabled = false
    }
}
