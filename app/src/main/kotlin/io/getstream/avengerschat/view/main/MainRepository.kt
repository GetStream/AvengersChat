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

package io.getstream.avengerschat.view.main

import androidx.annotation.WorkerThread
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import io.getstream.avengerschat.network.MarvelService
import io.getstream.avengerschat.persistence.AvengersDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val marvelService: MarvelService,
    private val avengersDao: AvengersDao,
    private val dispatcher: CoroutineDispatcher
) {

    init {
        Timber.d("injection MainRepository")
    }

    /**
     * loads avengers via fetching from network or getting from a local database.
     */
    @WorkerThread
    fun loadAvengers(
        onError: (String) -> Unit
    ) = flow {
        val avengers = avengersDao.getAvengers()
        if (avengers.isEmpty()) {
            val response = marvelService.fetchAvengers()
            response.suspendOnSuccess {
                avengersDao.insertAvengers(data)
                emit(data)
                Timber.d("suspendOnSuccess: $data")
            }.onError {
                onError(message())
                Timber.d("onError: ${message()}")
            }.onException {
                onError(message())
                Timber.d("OnException: ${message()}")
            }
        } else {
            emit(avengers)
        }
    }.flowOn(dispatcher)
}
