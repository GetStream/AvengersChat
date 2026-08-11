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

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.skydoves.bindables.binding
import io.getstream.avengerschat.core.uicomponents.extensions.adapterPositionOrNull
import io.getstream.avengerschat.extensions.lastActive
import io.getstream.chat.android.models.User
import io.getstream.feature.dm.databinding.ItemDirectMessageBinding

class DirectMessageAdapter constructor(
  private val onItemClicked: (User) -> Unit
) : ListAdapter<User, DirectMessageAdapter.DirectMessageViewHolder>(DIFF_CALLBACK) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DirectMessageViewHolder {
    return DirectMessageViewHolder(parent.binding(R.layout.item_direct_message))
  }

  override fun onBindViewHolder(holder: DirectMessageViewHolder, position: Int) {
    holder.bindUser(getItem(position))
  }

  inner class DirectMessageViewHolder(private val binding: ItemDirectMessageBinding) :
    RecyclerView.ViewHolder(binding.root) {

    init {
      binding.root.setOnClickListener {
        val position = adapterPositionOrNull ?: return@setOnClickListener
        onItemClicked.invoke(getItem(position))
      }
    }

    fun bindUser(user: User) {
      binding.user = user
      binding.lastActiveTextView.text = user.lastActive(binding.root.context)
      binding.executePendingBindings()
    }
  }

  companion object {
    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<User>() {
      override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
      }

      override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
      }
    }
  }
}
