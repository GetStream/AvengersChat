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

package io.getstream.avengerschat.feature.chat.component

import android.view.View
import androidx.lifecycle.LifecycleOwner
import io.getstream.avengerschat.core.uicomponents.stream.StreamComponents
import io.getstream.avengerschat.core.uicomponents.stream.StreamUIComponent
import io.getstream.avengerschat.feature.chat.R
import io.getstream.chat.android.ui.common.state.messages.Edit
import io.getstream.chat.android.ui.common.state.messages.MessageMode
import io.getstream.chat.android.ui.feature.messages.composer.MessageComposerView
import io.getstream.chat.android.ui.feature.messages.header.MessageListHeaderView
import io.getstream.chat.android.ui.feature.messages.list.MessageListView
import io.getstream.chat.android.ui.viewmodel.messages.MessageComposerViewModel
import io.getstream.chat.android.ui.viewmodel.messages.MessageListHeaderViewModel
import io.getstream.chat.android.ui.viewmodel.messages.MessageListViewModel
import io.getstream.chat.android.ui.viewmodel.messages.MessageListViewModelFactory
import io.getstream.chat.android.ui.viewmodel.messages.bindView

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
  private val messageInputViewModel: MessageComposerViewModel by lazy(LazyThreadSafetyMode.NONE) {
    factory.create(MessageComposerViewModel::class.java)
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
      view.findViewById<MessageComposerView>(R.id.messageInputView)
    messageInputView?.let {
      messageInputViewModel.bindView(it, lifecycleOwner)
    }

    // observe thread modes states.
    messageListViewModel.mode.observe(lifecycleOwner) {
      when (it) {
        is MessageMode.MessageThread -> {
          messageListHeaderViewModel.setActiveThread(it.parentMessage)
        }
        is MessageMode.Normal -> {
          messageListHeaderViewModel.resetThread()
        }
      }
      messageInputViewModel.setMessageMode(it)
    }
    messageListView?.setMessageEditHandler {
      messageInputViewModel.performMessageAction(Edit(it))
    }
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
