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

package io.stream.avengerschat.view.main.guest

import androidx.databinding.Bindable
import com.skydoves.bindables.BindingViewModel
import com.skydoves.bindables.asBindingProperty
import dagger.hilt.android.lifecycle.HiltViewModel
import io.stream.avengerschat.extensions.emptyString
import io.stream.avengerschat.model.Avenger
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class GuestViewModel @Inject constructor(
    private val guestRepository: GuestRepository
) : BindingViewModel() {

    private val nameStateFlow: MutableStateFlow<String> = MutableStateFlow(emptyString)

    val tokenFlow: Flow<String> = nameStateFlow.filter { it != emptyString }
        .flatMapLatest { guestRepository.fetchGuestToken(it) }

    @get:Bindable
    val enabled: Boolean by nameStateFlow.map { it == emptyString }.asBindingProperty(true)

    init {
        Timber.d("injection YouViewModel")
    }

    fun submitName(name: String) {
        nameStateFlow.value = name
    }

    fun createGuestAvenger(avenger: Avenger, token: String, quote: String): Avenger {
        val name = nameStateFlow.value
        return avenger.copy(
            id = name,
            name = name,
            token = token,
            quote = quote
        )
    }
}
