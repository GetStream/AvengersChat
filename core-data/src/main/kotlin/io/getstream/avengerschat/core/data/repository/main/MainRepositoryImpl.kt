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

package io.getstream.avengerschat.core.data.repository.main

import androidx.annotation.WorkerThread
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.suspendOnSuccess
import io.getstream.avengerschat.core.database.AvengersDao
import io.getstream.avengerschat.core.database.entity.mapper.asDomain
import io.getstream.avengerschat.core.database.entity.mapper.asEntity
import io.getstream.avengerschat.core.network.AppDispatchers
import io.getstream.avengerschat.core.network.Dispatcher
import io.getstream.avengerschat.core.network.service.MarvelService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject

internal class MainRepositoryImpl @Inject constructor(
  private val marvelService: MarvelService,
  private val avengersDao: AvengersDao,
  @Dispatcher(AppDispatchers.IO) private val dispatcher: CoroutineDispatcher
) : MainRepository {

  init {
    Timber.d("injection MainRepository")
  }

  /**
   * loads avengers via fetching from network or getting from a local database.
   */
  @WorkerThread
  override fun loadAvengers(
    onError: (String) -> Unit
  ) = flow {
    val avengers = avengersDao.getAvengers()
    if (avengers.isEmpty()) {
      val response = marvelService.fetchAvengers()
      response.suspendOnSuccess {
        avengersDao.insertAvengers(data.map { it.asEntity() })
        emit(data)
        Timber.d("suspendOnSuccess: $data")
      }.onFailure {
        onError(message())
        Timber.d("onError: ${message()}")
      }
    } else {
      emit(avengers.map { it.asDomain() })
    }
  }.flowOn(dispatcher)
}
