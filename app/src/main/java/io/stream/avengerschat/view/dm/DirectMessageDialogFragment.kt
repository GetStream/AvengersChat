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

package io.stream.avengerschat.view.dm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.skydoves.bindables.BindingBottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import io.getstream.chat.android.client.models.User
import io.stream.avengerschat.R
import io.stream.avengerschat.databinding.DialogFragmentDirectMessageBinding
import kotlinx.coroutines.flow.collect
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
                findNavController().navigate(
                    DirectMessageDialogFragmentDirections.actionToFragmentMessageList(it, null)
                )
                dismissAllowingStateLoss()
            }
        }
    }

    companion object {
        const val TAG = "DirectMessageDialogFragment"
    }
}
