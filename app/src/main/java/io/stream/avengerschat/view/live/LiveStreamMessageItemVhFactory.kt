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

package io.stream.avengerschat.view.live

import android.view.LayoutInflater
import android.view.ViewGroup
import com.getstream.sdk.chat.adapter.MessageListItem
import io.getstream.chat.android.ui.message.list.adapter.BaseMessageItemViewHolder
import io.getstream.chat.android.ui.message.list.adapter.MessageListItemPayloadDiff
import io.getstream.chat.android.ui.message.list.adapter.MessageListItemViewHolderFactory
import io.getstream.chat.android.ui.message.list.adapter.MessageListItemViewType
import io.stream.avengerschat.databinding.ItemLiveMessageBinding
import io.stream.avengerschat.extensions.localDate

/**
 * A custom item ViewHolderFactory for customizing the [MessageListItemViewType.PLAIN_TEXT]
 * of the message item.
 */
class LiveStreamMessageItemVhFactory private constructor() : MessageListItemViewHolderFactory() {

    override fun createViewHolder(
        parentView: ViewGroup,
        viewType: Int
    ): BaseMessageItemViewHolder<out MessageListItem> {
        return when (viewType) {
            MessageListItemViewType.PLAIN_TEXT -> PlainTextViewHolder(parentView)
            else -> super.createViewHolder(parentView, viewType)
        }
    }

    private class PlainTextViewHolder(
        parentView: ViewGroup,
        private val binding: ItemLiveMessageBinding = ItemLiveMessageBinding.inflate(
            LayoutInflater.from(parentView.context),
            parentView,
            false
        ),
    ) : BaseMessageItemViewHolder<MessageListItem.MessageItem>(binding.root) {

        override fun bindData(
            data: MessageListItem.MessageItem,
            diff: MessageListItemPayloadDiff?
        ) {
            binding.apply {
                this.data = data
                localDate = data.localDate(root.context)
                executePendingBindings()
            }
        }
    }

    companion object {
        fun create(): LiveStreamMessageItemVhFactory = LiveStreamMessageItemVhFactory()
    }
}
