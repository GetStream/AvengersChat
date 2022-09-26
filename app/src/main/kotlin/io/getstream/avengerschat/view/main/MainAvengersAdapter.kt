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

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.skydoves.bindables.binding
import io.getstream.avengerschat.R
import io.getstream.avengerschat.core.model.Avenger
import io.getstream.avengerschat.core.uicomponents.extensions.adapterPositionOrNull
import io.getstream.avengerschat.databinding.ItemAvengerBinding
import io.getstream.avengerschat.databinding.ItemGuestBinding
import io.getstream.avengerschat.feature.home.HomeActivity

class MainAvengersAdapter constructor(
  private val onItemYouClicked: (Avenger) -> Unit
) : ListAdapter<Avenger, MainAvengersAdapter.CharacterViewHolder>(DIFF_CALLBACK) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
    return when (viewType) {
      ItemViewType.HERO.viewType -> AvengersViewHolder(parent.binding(R.layout.item_avenger))
      ItemViewType.GUEST.viewType -> GuestViewHolder(parent.binding(R.layout.item_guest))
      else -> throw IllegalArgumentException("Wrong viewType: $viewType")
    }
  }

  override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
    if (holder is AvengersViewHolder) {
      holder.bindAvenger(getItem(position))
    }
  }

  override fun getItemViewType(position: Int): Int {
    return if (position == currentList.lastIndex) {
      ItemViewType.GUEST.viewType
    } else {
      ItemViewType.HERO.viewType
    }
  }

  fun getAvenger(position: Int): Avenger = getItem(position)

  abstract class CharacterViewHolder(rootBinding: ViewDataBinding) :
    RecyclerView.ViewHolder(rootBinding.root)

  inner class AvengersViewHolder(val binding: ItemAvengerBinding) : CharacterViewHolder(binding) {

    init {
      binding.root.setOnClickListener {
        val position = adapterPositionOrNull ?: return@setOnClickListener
        HomeActivity.startActivity(binding.transformationLayout, currentList[position])
      }
    }

    fun bindAvenger(avenger: Avenger) {
      binding.avenger = avenger
      binding.executePendingBindings()
    }
  }

  inner class GuestViewHolder(binding: ItemGuestBinding) : CharacterViewHolder(binding) {

    init {
      binding.root.setOnClickListener {
        val position = adapterPositionOrNull ?: return@setOnClickListener
        onItemYouClicked.invoke(currentList[position])
      }
    }
  }

  private enum class ItemViewType(val viewType: Int) {
    HERO(0),
    GUEST(1),
  }

  companion object {
    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Avenger>() {
      override fun areItemsTheSame(oldItem: Avenger, newItem: Avenger): Boolean {
        return oldItem.id == newItem.id
      }

      override fun areContentsTheSame(oldItem: Avenger, newItem: Avenger): Boolean {
        return oldItem == newItem
      }
    }
  }
}
