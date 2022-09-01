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
import io.getstream.avengerschat.BuildConfig
import io.getstream.avengerschat.R
import io.getstream.avengerschat.view.main.MainActivity
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.logger.ChatLogLevel
import io.getstream.chat.android.client.notifications.handler.NotificationConfig
import io.getstream.chat.android.client.notifications.handler.NotificationHandler
import io.getstream.chat.android.client.notifications.handler.NotificationHandlerFactory
import io.getstream.chat.android.offline.model.message.attachments.UploadAttachmentsNetworkType
import io.getstream.chat.android.offline.plugin.configuration.Config
import io.getstream.chat.android.offline.plugin.factory.StreamOfflinePluginFactory
import io.getstream.chat.android.pushprovider.firebase.FirebasePushDeviceGenerator
import timber.log.Timber

/**
 * StreamChatInitializer initializes all Stream Client components.
 */
class StreamChatInitializer : Initializer<Unit> {

    override fun create(context: Context) {
        Timber.d("StreamChatInitializer is initialized")

        val logLevel = if (BuildConfig.DEBUG) ChatLogLevel.ALL else ChatLogLevel.NOTHING
        val offlinePluginFactory = StreamOfflinePluginFactory(
            config = Config(
                backgroundSyncEnabled = true,
                userPresence = true,
                persistenceEnabled = true,
                uploadAttachmentsNetworkType = UploadAttachmentsNetworkType.NOT_ROAMING,
            ),
            appContext = context,
        )

        /**
         * initialize a global instance of the [ChatClient].
         * The ChatClient is the main entry point for all low-level operations on chat.
         * e.g, connect/disconnect user to the server, send/update/pin message, etc.
         */
        ChatClient.Builder(context.getString(R.string.stream_api_key), context)
            .notifications(createNotificationConfig(), createNotificationHandler(context))
            .withPlugin(offlinePluginFactory)
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
        listOf(FirebaseInitializer::class.java)
}
