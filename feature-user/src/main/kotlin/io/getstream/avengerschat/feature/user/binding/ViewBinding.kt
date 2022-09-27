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

package io.getstream.avengerschat.feature.user.binding

import androidx.appcompat.widget.AppCompatImageButton
import androidx.databinding.BindingAdapter
import io.getstream.avengerschat.core.uicomponents.extensions.drawable
import io.getstream.avengerschat.feature.user.R

internal object ViewBinding {

  @JvmStatic
  @BindingAdapter("sendUrlBtn")
  fun bindSendUrlButton(imageButton: AppCompatImageButton, enabled: Boolean) {
    val context = imageButton.context
    imageButton.isEnabled = enabled
    imageButton.background = if (enabled) {
      context.drawable(R.drawable.shape_circle)
    } else {
      context.drawable(R.drawable.shape_circle_disabled)
    }
  }
}
