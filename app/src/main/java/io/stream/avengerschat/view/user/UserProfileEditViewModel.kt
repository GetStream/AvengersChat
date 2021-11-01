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

package io.stream.avengerschat.view.user

import androidx.databinding.Bindable
import com.skydoves.bindables.BindingViewModel
import com.skydoves.bindables.bindingProperty
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class UserProfileEditViewModel @Inject constructor() : BindingViewModel() {

    @get:Bindable
    var sendEnabled: Boolean by bindingProperty(false)

    @get:Bindable
    var profileUrl: String? by bindingProperty(null)

    init {
        Timber.d("injection UserProfileEditViewModel")
    }
}
