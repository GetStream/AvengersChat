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

package io.getstream.avengerschat.view.user

import android.webkit.URLUtil
import androidx.databinding.Bindable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.skydoves.bindables.BindingViewModel
import com.skydoves.bindables.bindingProperty
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import io.getstream.avengerschat.model.Avenger
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import timber.log.Timber

class UserProfileEditViewModel @AssistedInject constructor(
    private val userProfileEditRepository: UserProfileEditRepository,
    @Assisted private val avenger: Avenger,
) : BindingViewModel() {

    @get:Bindable
    var sendEnabled: Boolean by bindingProperty(false)

    @get:Bindable
    var profileUrl: String? by bindingProperty(null)

    private val _sendUrlSateFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val sendUrlSateFlow: StateFlow<Boolean> = _sendUrlSateFlow
    val updatedUser = sendUrlSateFlow.filter { it && profileUrl != null }.flatMapLatest {
        userProfileEditRepository.updateUser(avenger, profileUrl!!)
    }

    init {
        Timber.d("injection UserProfileEditViewModel")
    }

    fun sendUrl() = _sendUrlSateFlow.tryEmit(URLUtil.isNetworkUrl(profileUrl))

    @dagger.assisted.AssistedFactory
    interface AssistedFactory {
        fun create(avenger: Avenger): UserProfileEditViewModel
    }

    companion object {
        fun provideFactory(
            assistedFactory: AssistedFactory,
            avenger: Avenger
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(avenger) as T
            }
        }
    }
}
