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

package io.getstream.avengerschat.core.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.getstream.avengerschat.core.data.repository.dm.DirectMessageRepository
import io.getstream.avengerschat.core.data.repository.dm.DirectMessageRepositoryImpl
import io.getstream.avengerschat.core.data.repository.guest.GuestRepository
import io.getstream.avengerschat.core.data.repository.guest.GuestRepositoryImpl
import io.getstream.avengerschat.core.data.repository.home.HomeRepository
import io.getstream.avengerschat.core.data.repository.home.HomeRepositoryImpl
import io.getstream.avengerschat.core.data.repository.main.MainRepository
import io.getstream.avengerschat.core.data.repository.main.MainRepositoryImpl
import io.getstream.avengerschat.core.data.repository.user.UserProfileEditRepository
import io.getstream.avengerschat.core.data.repository.user.UserProfileEditRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {

  @Binds
  fun bindsMainRepository(
    mainRepositoryImpl: MainRepositoryImpl
  ): MainRepository

  @Binds
  fun bindsHomeRepository(
    homeRepositoryImpl: HomeRepositoryImpl
  ): HomeRepository

  @Binds
  fun bindsDirectMessageRepository(
    directMessageRepositoryImpl: DirectMessageRepositoryImpl
  ): DirectMessageRepository

  @Binds
  fun bindsGuestRepository(
    guestRepositoryImpl: GuestRepositoryImpl
  ): GuestRepository

  @Binds
  fun bindsUserProfileEditRepository(
    userProfileEditRepositoryImpl: UserProfileEditRepositoryImpl
  ): UserProfileEditRepository
}
