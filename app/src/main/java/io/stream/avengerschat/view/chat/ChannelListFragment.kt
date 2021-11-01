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

package io.stream.avengerschat.view.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.skydoves.bindables.BindingFragment
import dagger.hilt.android.AndroidEntryPoint
import io.stream.avengerschat.R
import io.stream.avengerschat.components.StreamChannelListUIComponent
import io.stream.avengerschat.components.streamChannelListComponent
import io.stream.avengerschat.databinding.FragmentChannelListBinding
import io.stream.avengerschat.view.dm.DirectMessageDialogFragment
import io.stream.avengerschat.view.home.HomeViewModel
import io.stream.avengerschat.view.user.UserProfileDialogFragment

@AndroidEntryPoint
class ChannelListFragment :
    BindingFragment<FragmentChannelListBinding>(R.layout.fragment_channel_list) {

    private val viewModel: HomeViewModel by activityViewModels()
    private val streamChannelListUIComponent: StreamChannelListUIComponent by streamChannelListComponent()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return binding {
            vm = viewModel
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding {
            streamChannelListUIComponent.bindLayout(root)

            channelListHeaderView.setOnUserAvatarClickListener {
                UserProfileDialogFragment().show(parentFragmentManager, UserProfileDialogFragment.TAG)
            }

            channelListHeaderView.setOnActionButtonClickListener {
                DirectMessageDialogFragment().show(
                    parentFragmentManager,
                    DirectMessageDialogFragment.TAG
                )
            }

            channelListView.setChannelItemClickListener { channel ->
                findNavController().navigate(
                    ChannelListFragmentDirections.actionToFragmentMessageList(
                        channel.cid, null
                    )
                )
            }
        }
    }
}
