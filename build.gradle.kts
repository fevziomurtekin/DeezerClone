buildscript {
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
    }
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

