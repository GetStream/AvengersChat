/*
 * Copyright 2022 Stream.IO, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@file:Suppress("unused")

package io.getstream.avengerschat.initializer

import android.content.Context
import androidx.startup.Initializer
import io.getstream.avengerschat.view.custom.StreamGlobalStyles
import timber.log.Timber

class StreamGlobalStyleInitializer : Initializer<Unit> {

  override fun create(context: Context) {
    Timber.d("StreamGlobalStyleInitializer is initialized")

    StreamGlobalStyles.initializeReactionsGlobalStyles(context)
  }

  override fun dependencies(): List<Class<out Initializer<*>>> =
    listOf(
      TimberInitializer::class.java,
      StreamChatInitializer::class.java
    )
}
