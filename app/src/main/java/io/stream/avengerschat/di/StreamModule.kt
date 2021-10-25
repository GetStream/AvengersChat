/*
 * Copyright 2021 Stream.IO, Inc. All Rights Reserved.
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

package io.stream.avengerschat.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.logger.ChatLogLevel
import io.getstream.chat.android.livedata.ChatDomain
import io.stream.avengerschat.R
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StreamModule {

    @Provides
    @Singleton
    fun provideStreamChatClient(
        @ApplicationContext context: Context
    ): ChatClient {
        /**
         * initialize a global instance of the [ChatClient].
         * The ChatClient is the main entry point for all low-level operations on chat.
         * e.g, connect/disconnect user to the server, send/update/pin message, etc.
         */
        val chatClient: ChatClient =
            ChatClient.Builder(context.getString(R.string.stream_api_key), context)
                .logLevel(ChatLogLevel.ALL)
                .build()

        /**
         * initialize a global instance of the [ChatDomain].
         * The ChatDomain is the main entry point for all livedata & offline operations on chat.
         * e.g, querying available channel lists, querying users, etc.
         */
        ChatDomain
            .Builder(chatClient, context)
            .offlineEnabled()
            .build()

        return chatClient
    }

    @Provides
    @Singleton
    fun provideStreamChatDomain(): ChatDomain {
        return ChatDomain.instance()
    }
}
