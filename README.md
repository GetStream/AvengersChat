<h1 align="center">Avengers Chat</h1>

<p align="center">
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
  <a href="https://android-arsenal.com/api?level=21"><img alt="API" src="https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat"/></a>
</p>

<p align="center">  
AvengersChat is a demo application based on modern Android tech stacks and Stream's Chat SDK.
</br>

<p align="center">
<img src="/previews/screenshot.jpg" />
</p>

## Previews
<p align="center">
<img src="/previews/preview0.gif" width="32%"/>
<img src="/previews/preview1.gif" width="32%"/>
<img src="/previews/preview2.gif" width="32%"/>
</p>


## Stream Chat SDK
AvengersChat has been built with [Stream Chat SDK](https://getstream.io/) for implementing messaging systems.
- [Chat Messaging Tutorial](https://getstream.io/tutorials/android-chat/) - Basic tutorials for getting started by building a simple messaging app.
- [Stream Chat Android repository](https://github.com/GetStream/stream-chat-android) - Official Android SDK for Stream Chat.
- [UI Components Sample](https://github.com/GetStream/stream-chat-android/tree/main/stream-chat-android-ui-components-sample) - Official sample app for building messeging app. 
- [UI Components Documentation](https://getstream.io/chat/docs/sdk/android/) - Full documentation of the Stream UI Components.
- [Chat Client Documentation](https://getstream.io/chat/docs/android/?language=kotlin) - Full documentation of the Chat client for requesting API calls.


<img src="/previews/preview0.gif" align="right" width="32%"/>

## Tech stack & Open source libraries
- Minimum SDK level 21.
- 100% [Kotlin](https://kotlinlang.org/) based + [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous.
- Hilt for dependency injection.
- JetPack
  - Lifecycle - dispose observing data when lifecycle state changes.
  - ViewModel - UI related data holder, lifecycle aware.
  - Room Persistence - construct database.
  - App Startup - Provides a straightforward, performant way to initialize components at application startup.
- Architecture
  - MVVM Architecture (View - DataBinding - ViewModel - Model)
  - Repository pattern
- [Material Design & Animations](https://material.io/design) - Design system created by Google to help teams build high-quality digital experiences.
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit) - Construct the REST APIs and paging network data.
- [Sandwich](https://github.com/skydoves/Sandwich) - Construct lightweight http API response and handling error responses.
- [Moshi](https://github.com/square/moshi/) - A modern JSON library for Kotlin and Java.
- [Coil](https://github.com/coil-kt/coil) - Image loading for Android backed by Kotlin Coroutines.
- [TransformationLayout](https://github.com/skydoves/transformationlayout) - implementing transformation motion animations.
- [android-youtube-player](https://github.com/PierfrancescoSoffritti/android-youtube-player) - YouTube Player library for Android and Chromecast, stable and customizable.
- [DiscreteScrollView](https://github.com/yarolegovich/DiscreteScrollView) - Implementing a scrollable list of items.
- [Timber](https://github.com/JakeWharton/timber) - Logging.

## MAD Score
![summary](https://user-images.githubusercontent.com/24237865/102366914-84f6b000-3ffc-11eb-8d49-b20694239782.png)



# License
```xml
Copyright 2021 Stream.IO, Inc. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

