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

package io.getstream.avengerschat.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.getstream.avengerschat.initializer.StreamChatInitializer
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.livedata.ChatDomain
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StreamModule {

    @Provides
    @Singleton
    fun provideStreamChatClient(): ChatClient {
        /**
         * Provides an instance of the [ChatClient] which is initialized in [StreamChatInitializer].
         */
        return ChatClient.instance()
    }

    @Provides
    @Singleton
    fun provideStreamChatDomain(): ChatDomain {
        /**
         * Provides an instance of the [ChatDomain] which is initialized in [StreamChatInitializer].
         */
        return ChatDomain.instance()
    }
}
