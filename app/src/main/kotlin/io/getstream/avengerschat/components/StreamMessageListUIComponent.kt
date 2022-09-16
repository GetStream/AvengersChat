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

package io.getstream.avengerschat.components

import android.view.View
import androidx.lifecycle.LifecycleOwner
import com.getstream.sdk.chat.viewmodel.MessageInputViewModel
import com.getstream.sdk.chat.viewmodel.messages.MessageListViewModel
import io.getstream.avengerschat.R
import io.getstream.avengerschat.core.uicomponents.stream.StreamComponents
import io.getstream.avengerschat.core.uicomponents.stream.StreamUIComponent
import io.getstream.chat.android.ui.message.input.MessageInputView
import io.getstream.chat.android.ui.message.input.viewmodel.bindView
import io.getstream.chat.android.ui.message.list.MessageListView
import io.getstream.chat.android.ui.message.list.header.MessageListHeaderView
import io.getstream.chat.android.ui.message.list.header.viewmodel.MessageListHeaderViewModel
import io.getstream.chat.android.ui.message.list.header.viewmodel.bindView
import io.getstream.chat.android.ui.message.list.viewmodel.bindView
import io.getstream.chat.android.ui.message.list.viewmodel.factory.MessageListViewModelFactory

/**
 * Stream message list UI component.
 *
 * [Stream Message List](https://getstream.io/chat/docs/sdk/android/ui/components/message-list/)
 */
class StreamMessageListUIComponent constructor(
  override val lifecycleOwner: LifecycleOwner,
  val cid: String,
  val messageId: String? = null
) : StreamUIComponent {

  private val factory by lazy(LazyThreadSafetyMode.NONE) {
    MessageListViewModelFactory(cid = cid, messageId = messageId)
  }
  private val messageListHeaderViewModel: MessageListHeaderViewModel by lazy(LazyThreadSafetyMode.NONE) {
    factory.create(MessageListHeaderViewModel::class.java)
  }
  val messageListViewModel: MessageListViewModel by lazy(LazyThreadSafetyMode.NONE) {
    factory.create(MessageListViewModel::class.java)
  }
  private val messageInputViewModel: MessageInputViewModel by lazy(LazyThreadSafetyMode.NONE) {
    factory.create(MessageInputViewModel::class.java)
  }

  @StreamComponents
  override fun bindLayout(view: View) {
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
    messageListView?.setMessageEditHandler(messageInputViewModel::postMessageToEdit)
  }
}

@StreamComponents
@Suppress("UNCHECKED_CAST")
fun <T : StreamUIComponent> LifecycleOwner.streamMessageListComponent(
  messageIdProvider: (() -> String)? = null,
  cidProvider: () -> String
): Lazy<T> {
  return lazy(LazyThreadSafetyMode.NONE) {
    StreamMessageListUIComponent(
      lifecycleOwner = this,
      messageId = messageIdProvider?.invoke(),
      cid = cidProvider.invoke()
    ) as T
  }
}
