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

package io.getstream.avengerschat.core.database.di

import android.app.Application
import androidx.room.Room
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.getstream.avengerschat.core.database.AppDatabase
import io.getstream.avengerschat.core.database.AvengersDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

  @Provides
  @Singleton
  fun provideMoshi(): Moshi {
    return Moshi.Builder()
      .addLast(KotlinJsonAdapterFactory())
      .build()
  }

  @Provides
  @Singleton
  fun provideAppDatabase(
    application: Application
  ): AppDatabase {
    return Room
      .databaseBuilder(
        application,
        AppDatabase::class.java,
        "Avengers.db"
      )
      .fallbackToDestructiveMigration()
      .build()
  }

  @Provides
  @Singleton
  fun provideAvengersDao(appDatabase: AppDatabase): AvengersDao {
    return appDatabase.avengersDao()
  }
}
