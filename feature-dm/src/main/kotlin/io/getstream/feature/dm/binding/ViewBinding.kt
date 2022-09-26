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

package io.getstream.feature.dm.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import io.getstream.chat.android.client.models.User

internal object ViewBinding {

  @JvmStatic
  @BindingAdapter("adapterDirectMessage")
  fun bindAdapterQueriedAvengers(view: RecyclerView, user: List<User>?) {
    (view.adapter as? io.getstream.feature.dm.DirectMessageAdapter)?.submitList(user?.reversed())
  }
}
