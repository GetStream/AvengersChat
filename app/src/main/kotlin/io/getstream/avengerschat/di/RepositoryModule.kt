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

package io.getstream.avengerschat.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import io.getstream.avengerschat.network.MarvelService
import io.getstream.avengerschat.persistence.AvengersDao
import io.getstream.avengerschat.view.dm.DirectMessageRepository
import io.getstream.avengerschat.view.home.HomeRepository
import io.getstream.avengerschat.view.main.MainRepository
import io.getstream.avengerschat.view.user.UserProfileEditRepository
import io.getstream.chat.android.client.ChatClient
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideMainRepository(
        marvelService: MarvelService,
        avengersDao: AvengersDao,
        dispatcher: CoroutineDispatcher
    ): MainRepository {
        return MainRepository(marvelService, avengersDao, dispatcher)
    }

    @Provides
    @ViewModelScoped
    fun provideHomeRepository(
        chatClient: ChatClient,
        avengersDao: AvengersDao,
        dispatcher: CoroutineDispatcher
    ): HomeRepository {
        return HomeRepository(chatClient, avengersDao, dispatcher)
    }

    @Provides
    @ViewModelScoped
    fun provideDirecMessageRepository(
        chatClient: ChatClient,
        dispatcher: CoroutineDispatcher
    ): DirectMessageRepository {
        return DirectMessageRepository(chatClient, dispatcher)
    }

    @Provides
    @ViewModelScoped
    fun provideUserProfileEditRepository(
        chatClient: ChatClient,
        dispatcher: CoroutineDispatcher
    ): UserProfileEditRepository {
        return UserProfileEditRepository(chatClient, dispatcher)
    }
}
