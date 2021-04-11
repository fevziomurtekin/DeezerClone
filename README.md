<h1 align="center">Deezer Clone</h1>

<p align="center">
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
  <a href="https://github.com/fevziomurtekin/DeezerClone/actions"><img alt="Build Status" src="https://github.com/fevziomurtekin/DeezerClone/workflows/Android%20CI/badge.svg"/></a> 
 <a href="https://github.com/fevziomurtekin/DeezerClone/releases/"><img alt="GitHub release (latest by date)" src="https://img.shields.io/github/v/release/fevziomurtekin/DeezerClone?style=plastic"></a>

</p>

<p align="center">  
 Deezer Clone application, Dagger Hilt, Coroutines, Flow, Jetpack (Room, ViewModel,Navigation LiveData) based on MVVM architecture. Also fetching data from the network and integrating local data in the database via repository pattern.
</p>

<p align="center">
<img src="/art/banner.png"/>
</p>

## APK
Click and go to releases page [Releases](https://github.com/fevziomurtekin/DeezerClone/releases/) to download the latest APK.


## Tech stack - Library
- [Kotlin](https://kotlinlang.org/) , [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) , [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/)
- [Dagger-Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
- [Kotlin-DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html)
- JetPack
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) 
  - [Lifecycle](https://developer.android.com/jetpack/androidx/releases/lifecycle) 
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) 
  - [Room](https://developer.android.com/topic/libraries/architecture/room)
  - [Navigation](https://developer.android.com/guide/navigation/navigation-getting-started)
  - [Data Binding](https://developer.android.com/topic/libraries/data-binding)
  - [MVVM Architecture]() (View - DataBinding - ViewModel - Model)
  - Repository pattern
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit)
- [Gson](https://github.com/google/gson)
- [Timber](https://github.com/JakeWharton/timber)
- [Material-Components](https://github.com/material-components/material-components-android)
- [Coil](https://github.com/coil-kt/coil)
- [ShimmerLayout](http://facebook.github.io/shimmer-android/)
- [Detekt](https://detekt.github.io/detekt/)
- Test
  - [mockK](https://mockk.io/)
  - [Mock Server](https://www.mock-server.com/)
  - [Robolectric](http://robolectric.org/)
  - [Mockito Kotlin](https://github.com/nhaarman/mockito-kotlin)
  - [Turbine](https://cashapp.github.io/turbine/docs/0.x/turbine/)
  - [Junit](https://junit.org/junit5/)
  - [Navigation/Deeplink Test](https://github.com/android/architecture-components-samples/tree/main/NavigationAdvancedSample)

## Architecture
DeezerClone is based on MVVM architecture and a repository pattern.

![architecture](https://raw.githubusercontent.com/fevziomurtekin/hackernewsapp/master/screenshot/mvvm.png)

## Detekt
A static code analysis tool for the Kotlin programming language. It operates on the abstract syntax tree provided by the Kotlin compiler.
You can check the code by running the this command.

```properties
  ./gradlew detektDeezer
``` 

## API
Used Deezer API. Deezer API provides a RESTful API. [Link](https://developers.deezer.com/api/)

## MAD Scorecard

<a href="https://madscorecard.withgoogle.com/scorecard/share/3185391510/#summary"> <b> DeezerClone's MAD scorecard. </b> </a>

## Roadmap
- [x] Media player will be created using Exoplayer.
- [x] Favorites page will be created.
- [x] Application will be testing and is out new release.
- [ ] New features and tests can be added.
- [x] Base repository will created.
- [x] UI test will be writed.

## License
The Apache License 2.0 - see [`LICENSE`](LICENSE) for more details
