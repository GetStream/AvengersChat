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

package io.stream.avengerschat.view.home

import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.skydoves.bindables.BindingViewModel
import com.skydoves.bindables.asBindingProperty
import com.skydoves.bindables.bindingProperty
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import io.getstream.chat.android.client.models.ConnectionData
import io.getstream.chat.android.client.models.User
import io.getstream.chat.android.livedata.ChatDomain
import io.stream.avengerschat.extensions.parsedColor
import io.stream.avengerschat.model.Avenger
import io.stream.avengerschat.model.LiveRoomInfo
import timber.log.Timber

class HomeViewModel @AssistedInject constructor(
    private val homeRepository: HomeRepository,
    chatDomain: ChatDomain,
    @Assisted val avenger: Avenger,
) : BindingViewModel() {

    @get:Bindable
    val connectionData: ConnectionData?
        by homeRepository.connectUser(avenger).asBindingProperty(null)

    @get:Bindable
    val liveRoomInfoList: List<LiveRoomInfo>?
        by homeRepository.getLiveRoomInfo(avenger).asBindingProperty(null)

    @get:Bindable
    val parsedColor: Int by bindingProperty(avenger.parsedColor)

    @get:Bindable
    var visibleBottomNav: Boolean by bindingProperty(true)

    val user: LiveData<User?> = chatDomain.user

    val totalUnreadCount: LiveData<Int> = chatDomain.totalUnreadCount

    init {
        Timber.d("injection HomeViewModel")
    }

    override fun onCleared() {
        super.onCleared()

        // disconnects user login status.
        homeRepository.disconnectUser()
    }

    @dagger.assisted.AssistedFactory
    interface AssistedFactory {
        fun create(avenger: Avenger): HomeViewModel
    }

    companion object {
        fun provideFactory(
            assistedFactory: AssistedFactory,
            avenger: Avenger
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return assistedFactory.create(avenger) as T
            }
        }
    }
}
