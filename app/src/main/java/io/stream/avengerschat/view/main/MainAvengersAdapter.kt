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

package io.stream.avengerschat.view.main

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.skydoves.bindables.binding
import io.stream.avengerschat.R
import io.stream.avengerschat.databinding.ItemAvengerBinding
import io.stream.avengerschat.extensions.adapterPositionOrNull
import io.stream.avengerschat.model.Avenger
import io.stream.avengerschat.view.home.HomeActivity

class MainAvengersAdapter : ListAdapter<Avenger, MainAvengersAdapter.AvengersViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AvengersViewHolder {
        return AvengersViewHolder(parent.binding(R.layout.item_avenger))
    }

    override fun onBindViewHolder(holder: AvengersViewHolder, position: Int) {
        holder.bindAvenger(getItem(position))
    }

    fun getAvenger(position: Int): Avenger = getItem(position)

    inner class AvengersViewHolder(private val binding: ItemAvengerBinding) :
        RecyclerView.ViewHolder(binding.root) {

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
