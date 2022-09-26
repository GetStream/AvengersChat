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

package io.getstream.feature.dm

import androidx.databinding.Bindable
import com.skydoves.bindables.BindingViewModel
import com.skydoves.bindables.asBindingProperty
import dagger.hilt.android.lifecycle.HiltViewModel
import io.getstream.avengerschat.core.data.repository.dm.DirectMessageRepository
import io.getstream.chat.android.client.models.User
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DirectMessageViewModel @Inject constructor(
  private val directMessageRepository: DirectMessageRepository
) : BindingViewModel() {

  @get:Bindable
  val queriedAvengers: List<User>?
    by directMessageRepository.queryAvengers().asBindingProperty(null)

  init {
    Timber.d("injection DirectMessageViewMode")
  }

  fun joinNewChannel(user: User) = directMessageRepository.joinNewChannel(user)
}
