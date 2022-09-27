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

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.skydoves.bindables.BindingBottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import io.getstream.avengerschat.core.navigation.navigateToMessageList
import io.getstream.chat.android.client.models.User
import io.getstream.feature.dm.databinding.DialogFragmentDirectMessageBinding
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DirectMessageDialogFragment :
  BindingBottomSheetDialogFragment<DialogFragmentDirectMessageBinding>(R.layout.dialog_fragment_direct_message) {

  private val viewModel: DirectMessageViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setStyle(STYLE_NORMAL, R.style.BottomSheetStyle)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    super.onCreateView(inflater, container, savedInstanceState)
    return binding {
      adapter = DirectMessageAdapter(onItemClicked)
      vm = viewModel
    }.root
  }

  private val onItemClicked: (User) -> Unit = {
    viewLifecycleOwner.lifecycleScope.launch {
      viewModel.joinNewChannel(it).collect {
        navigateToMessageList(cid = it)
        dismissAllowingStateLoss()
      }
    }
  }

  companion object {
    const val TAG = "DirectMessageDialogFragment"

    fun create(): DirectMessageDialogFragment = DirectMessageDialogFragment()
  }
}
