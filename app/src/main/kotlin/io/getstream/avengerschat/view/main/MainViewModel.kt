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

package io.getstream.avengerschat.view.main

import androidx.annotation.VisibleForTesting
import androidx.databinding.Bindable
import androidx.lifecycle.viewModelScope
import com.skydoves.bindables.BindingViewModel
import com.skydoves.bindables.asBindingProperty
import com.skydoves.bindables.bindingProperty
import dagger.hilt.android.lifecycle.HiltViewModel
import io.getstream.avengerschat.model.Avenger
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    mainRepository: MainRepository
) : BindingViewModel() {

    @get:Bindable
    var errorMessage: String? by bindingProperty(null)
        private set

    @VisibleForTesting
    internal val avengersFlow = mainRepository.loadAvengers(
        onError = { errorMessage = it }
    )

    @get:Bindable
    val avengers: List<Avenger>? by avengersFlow.asBindingProperty(viewModelScope, null)

    init {
        Timber.d("injection MainViewModel")
    }
}
