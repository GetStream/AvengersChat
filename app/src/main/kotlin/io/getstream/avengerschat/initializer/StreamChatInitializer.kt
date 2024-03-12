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

package io.getstream.avengerschat.initializer

import android.content.Context
import android.content.Intent
import androidx.startup.Initializer
import io.getstream.android.push.firebase.FirebasePushDeviceGenerator
import io.getstream.avengerschat.BuildConfig
import io.getstream.avengerschat.R
import io.getstream.avengerschat.core.uicomponents.startup.StreamGlobalStyleInitializer
import io.getstream.avengerschat.view.main.MainActivity
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.logger.ChatLogLevel
import io.getstream.chat.android.client.notifications.handler.NotificationConfig
import io.getstream.chat.android.client.notifications.handler.NotificationHandler
import io.getstream.chat.android.client.notifications.handler.NotificationHandlerFactory
import io.getstream.chat.android.models.UploadAttachmentsNetworkType
import io.getstream.chat.android.offline.plugin.factory.StreamOfflinePluginFactory
import io.getstream.chat.android.state.plugin.config.StatePluginConfig
import io.getstream.chat.android.state.plugin.factory.StreamStatePluginFactory
import timber.log.Timber

/**
 * StreamChatInitializer initializes all Stream Client components.
 */
class StreamChatInitializer : Initializer<Unit> {

  override fun create(context: Context) {
    Timber.d("StreamChatInitializer is initialized")

    val logLevel = if (BuildConfig.DEBUG) ChatLogLevel.ALL else ChatLogLevel.NOTHING
    val offlinePluginFactory = StreamOfflinePluginFactory(appContext = context)
    val statePluginFactory = StreamStatePluginFactory(
      appContext = context,
      config = StatePluginConfig(
        backgroundSyncEnabled = true,
        userPresence = true
      )
    )

    /**
     * initialize a global instance of the [ChatClient].
     * The ChatClient is the main entry point for all low-level operations on chat.
     * e.g, connect/disconnect user to the server, send/update/pin message, etc.
     */
    ChatClient.Builder(context.getString(R.string.stream_api_key), context)
      .notifications(createNotificationConfig(), createNotificationHandler(context))
      .withPlugins(offlinePluginFactory, statePluginFactory)
      .uploadAttachmentsNetworkType(UploadAttachmentsNetworkType.NOT_ROAMING)
      .logLevel(logLevel)
      .build()
  }

  /**
   * Creates [NotificationConfig] that configures push notifications.
   */
  private fun createNotificationConfig(): NotificationConfig {
    return NotificationConfig(
      pushDeviceGenerators = listOf(
        FirebasePushDeviceGenerator()
      )
    )
  }

  /**
   * Creates [NotificationHandler] that handles new push notifications and
   * customizes an intent the user triggers when clicking on a notification.
   */
  private fun createNotificationHandler(
    context: Context
  ): NotificationHandler {
    return NotificationHandlerFactory.createNotificationHandler(
      context = context,
      newMessageIntent = { _: String, _: String, _: String ->
        Intent(context, MainActivity::class.java).apply {
          flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
      }
    )
  }

  override fun dependencies(): List<Class<out Initializer<*>>> =
    listOf(
      TimberInitializer::class.java,
      StreamGlobalStyleInitializer::class.java,
      FirebaseInitializer::class.java
    )
}
