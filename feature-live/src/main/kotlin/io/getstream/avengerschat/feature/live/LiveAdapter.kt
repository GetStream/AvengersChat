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

package io.getstream.avengerschat.feature.live

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.skydoves.bindables.binding
import io.getstream.avengerschat.core.model.LiveRoomInfo
import io.getstream.avengerschat.core.uicomponents.extensions.adapterPositionOrNull
import io.getstream.avengerschat.feature.live.databinding.ItemLiveBinding

class LiveAdapter constructor(
  private val onItemClicked: (LiveRoomInfo) -> Unit
) : ListAdapter<LiveRoomInfo, LiveAdapter.LiveViewHolder>(DIFF_CALLBACK) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LiveViewHolder {
    return LiveViewHolder(parent.binding(R.layout.item_live))
  }

  override fun onBindViewHolder(holder: LiveViewHolder, position: Int) {
    holder.bindLiveRoomInfo(getItem(position))
  }

  inner class LiveViewHolder(private val binding: ItemLiveBinding) :
    RecyclerView.ViewHolder(binding.root) {

    init {
      binding.root.setOnClickListener {
        val position = adapterPositionOrNull ?: return@setOnClickListener
        onItemClicked.invoke(getItem(position))
      }
    }

    fun bindLiveRoomInfo(liveRoomInfo: LiveRoomInfo) {
      binding.info = liveRoomInfo
      binding.executePendingBindings()
    }
  }

  companion object {
    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<LiveRoomInfo>() {
      override fun areItemsTheSame(oldItem: LiveRoomInfo, newItem: LiveRoomInfo): Boolean {
        return oldItem.cid == newItem.cid
      }

      override fun areContentsTheSame(oldItem: LiveRoomInfo, newItem: LiveRoomInfo): Boolean {
        return oldItem == newItem
      }
    }
  }
}
