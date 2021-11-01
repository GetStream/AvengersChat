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

package io.stream.avengerschat.view.dm

import androidx.annotation.WorkerThread
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.api.models.QueryUsersRequest
import io.getstream.chat.android.client.call.await
import io.getstream.chat.android.client.models.Filters
import io.getstream.chat.android.client.models.User
import io.getstream.chat.android.client.utils.onSuccessSuspend
import io.stream.avengerschat.extensions.currentUserId
import io.stream.avengerschat.network.Api.STREAM_CHANNEL_TYPE_MESSAGING
import io.stream.avengerschat.network.Api.STREAM_USER_ID
import io.stream.avengerschat.network.Api.STREAM_USER_ROLE
import io.stream.avengerschat.network.Api.STREAM_USER_ROLE_ADMIN
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject

class DirectMessageRepository @Inject constructor(
    private val chatClient: ChatClient,
    private val dispatcher: CoroutineDispatcher
) {

    init {
        Timber.d("injection DirectMessageRepository")
    }

    /**
     * requests query to the Stream Client server for getting a filtered list of Avengers.
     */
    @WorkerThread
    fun queryAvengers() = flow {
        val usersRequest = QueryUsersRequest(
            filter = Filters.and(
                Filters.ne(STREAM_USER_ID, chatClient.currentUserId),
                Filters.ne(STREAM_USER_ROLE, STREAM_USER_ROLE_ADMIN),
            ),
            offset = 0,
            limit = 20,
        )
        val result = chatClient.queryUsers(usersRequest).await()
        result.onSuccessSuspend {
            emit(result.data())
        }
    }.flowOn(dispatcher)

    /**
     * requests to create a new channel if there is no existing or joins if there is an existing channel.
     */
    @WorkerThread
    fun joinNewChannel(user: User) = flow {
        val result = chatClient.createChannel(
            channelType = STREAM_CHANNEL_TYPE_MESSAGING,
            members = listOf(user.id, chatClient.currentUserId),
            extraData = mapOf()
        ).await()
        result.onSuccessSuspend {
            emit(result.data().cid)
        }
    }
}
