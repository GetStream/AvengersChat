<h1 align="center">Avengers Chat</h1>

<p align="center">
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
  <a href="https://android-arsenal.com/api?level=21"><img alt="API" src="https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat"/></a>
  <a href="https://mailchi.mp/kotlinweekly/kotlin-weekly-274"><img alt="KotlinWeekly" src="https://skydoves.github.io/badges/kotlin-weekly.svg"/></a>
  <a href="https://github.com/skydoves/AvengersChat/actions/workflows/android.yml"><img alt="API" src="https://github.com/skydoves/AvengersChat/actions/workflows/android.yml/badge.svg"/></a>
</p>

<p align="center">  
AvengersChat is a demo application based on modern Android tech stacks and Stream Chat SDK.
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

## Download
Go to the [Releases](https://github.com/skydoves/AvengersChat/releases) to download the latest APK.

<img src="https://user-images.githubusercontent.com/24237865/138428440-b92e5fb7-89f8-41aa-96b1-71a5486c5849.png" align="right" width="12%"/>


## Stream Chat SDK
AvengersChat has been built with [Stream Chat SDK](https://getstream.io/) for implementing messaging systems.
- [Chat Messaging Tutorial](https://getstream.io/tutorials/android-chat/) - Basic tutorials for getting started by building a simple messaging app.
- [Stream Chat Android repository](https://github.com/GetStream/stream-chat-android) - Official Android SDK for Stream Chat.
- [Chat Client Documentation](https://getstream.io/chat/docs/android/?language=kotlin) - Full documentation of the Chat client for requesting API calls. 
- [UI Components Documentation](https://getstream.io/chat/docs/sdk/android/) - Full documentation of the Stream UI Components.
- [UI Components Sample](https://github.com/GetStream/stream-chat-android/tree/main/stream-chat-android-ui-components-sample) - Official sample app for building messaging app.


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
  - [Bindables](https://github.com/skydoves/bindables) - Android DataBinding kit for notifying data changes to UI layers.
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

<img src="/previews/preview3.gif" align="right" width="32%"/>

## Supported features
- Light and Dark themes.
- Different color themes by a chosen character.
- 100% offline mode supports.
- Live stream chat example.
- Channel and message lists.
- Direct message and group messages.
- Mentioned message list.
- Custom emoji reactions.
- Guest mode.
- User online/offline status.
- User last active date.
- User info & direct message dialogs.
- Visual animations like transition.
- And a lot of features by Stream Android SDK!

## TODO
Anyone can contribute to our open source project! Please kindly read this [Contributing Guideline](https://github.com/skydoves/AvengersChat/blob/main/CONTRIBUTING.md) before contributing to our project. 😎
- [x] Modify a user profile image.
- [x] Login with a new guest user.
- [ ] Create group chats by selecting users.
- [ ] Access user info dialog everywhere.
- [ ] Push notifications for new messages.
- [ ] Add unit test cases for all business logic.

## Chat screenshots
If you find some funny moments on this demo application, feel free to join and share that moment in [this thread](https://github.com/skydoves/AvengersChat/discussions/1)!
<p align="center">
<img src="https://user-images.githubusercontent.com/24237865/139976413-675a3b32-1edf-4bfc-b600-a8ffb734f1ec.jpeg" width="32%"/>
<img src="https://user-images.githubusercontent.com/24237865/139976435-5da608b9-e453-47c0-be78-73f0039b4ab1.jpeg" width="32%"/>
<img src="https://user-images.githubusercontent.com/24237865/139976345-dedc9c4c-2140-4fbc-9d02-7d815380d900.jpeg" width="32%"/>
</p>

## Architecture
![architecture-android-stream](https://user-images.githubusercontent.com/24237865/138990023-98e21858-534a-4607-addf-cf828139ccd2.jpg)


## MAD Score
![summary](https://user-images.githubusercontent.com/24237865/102366914-84f6b000-3ffc-11eb-8d49-b20694239782.png)


## Find this repository useful? 💙
Support it by joining __[stargazers](https://github.com/skydoves/AvengersChat/stargazers)__ for this repository. :star: <br>

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

