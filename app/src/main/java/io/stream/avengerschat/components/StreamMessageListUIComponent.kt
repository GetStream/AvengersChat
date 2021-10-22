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

package io.stream.avengerschat.components

import android.view.View
import androidx.lifecycle.LifecycleOwner
import com.getstream.sdk.chat.viewmodel.MessageInputViewModel
import com.getstream.sdk.chat.viewmodel.messages.MessageListViewModel
import io.getstream.chat.android.ui.message.input.MessageInputView
import io.getstream.chat.android.ui.message.input.viewmodel.bindView
import io.getstream.chat.android.ui.message.list.MessageListView
import io.getstream.chat.android.ui.message.list.header.MessageListHeaderView
import io.getstream.chat.android.ui.message.list.header.viewmodel.MessageListHeaderViewModel
import io.getstream.chat.android.ui.message.list.header.viewmodel.bindView
import io.getstream.chat.android.ui.message.list.viewmodel.bindView
import io.getstream.chat.android.ui.message.list.viewmodel.factory.MessageListViewModelFactory
import io.stream.avengerschat.R

/**
 * Stream message list UI component.
 *
 * [Stream Message List](https://getstream.io/chat/docs/sdk/android/ui/components/message-list/)
 */
class StreamMessageListUIComponent constructor(
    override val lifecycleOwner: LifecycleOwner
) : StreamUIComponent {

    private lateinit var cid: String

    private var messageId: String? = null

    private val threadSafetyMode: LazyThreadSafetyMode = LazyThreadSafetyMode.NONE

    private val factory by lazy(threadSafetyMode) {
        MessageListViewModelFactory(cid = cid)
    }
    private val messageListHeaderViewModel: MessageListHeaderViewModel by lazy(threadSafetyMode) {
        factory.create(MessageListHeaderViewModel::class.java)
    }
    val messageListViewModel: MessageListViewModel by lazy(threadSafetyMode) {
        factory.create(MessageListViewModel::class.java)
    }
    private val messageInputViewModel: MessageInputViewModel by lazy(threadSafetyMode) {
        factory.create(MessageInputViewModel::class.java)
    }

    /**
     * initializes the channel id and message-id for connecting a specific channel.
     */
    @StreamComponentHighlighter
    fun initIds(cid: String, messageId: String? = null) {
        this.cid = cid
        this.messageId = messageId
    }

    @StreamComponentHighlighter
    override fun bindLayout(view: View) {
        if (!::cid.isInitialized) {
            throw UninitializedPropertyAccessException("cid must be initialized before to bind layout.")
        }

        // binds Stream UI components to the ViewModels.
        val messageListHeaderView =
            view.findViewById<MessageListHeaderView>(R.id.messageListHeaderView)
        messageListHeaderView?.let {
            messageListHeaderViewModel.bindView(it, lifecycleOwner)
        }
        val messageListView =
            view.findViewById<MessageListView>(R.id.messageListView)
        messageListView?.let {
            messageListViewModel.bindView(it, lifecycleOwner)
        }
        val messageInputView =
            view.findViewById<MessageInputView>(R.id.messageInputView)
        messageInputView?.let {
            messageInputViewModel.bindView(it, lifecycleOwner)
        }

        // observe thread modes states.
        messageListViewModel.mode.observe(lifecycleOwner) {
            when (it) {
                is MessageListViewModel.Mode.Thread -> {
                    messageListHeaderViewModel.setActiveThread(it.parentMessage)
                    messageInputViewModel.setActiveThread(it.parentMessage)
                }
                is MessageListViewModel.Mode.Normal -> {
                    messageListHeaderViewModel.resetThread()
                    messageInputViewModel.resetThread()
                }
            }
        }
        messageListView.setMessageEditHandler(messageInputViewModel::postMessageToEdit)
    }
}

@StreamComponentHighlighter
fun LifecycleOwner.streamMessageListComponent(): Lazy<StreamMessageListUIComponent> {
    return lazy(LazyThreadSafetyMode.NONE) {
        StreamMessageListUIComponent(lifecycleOwner = this)
    }
}
