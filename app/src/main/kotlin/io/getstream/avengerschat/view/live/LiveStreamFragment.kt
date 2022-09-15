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

package io.getstream.avengerschat.view.live

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.skydoves.bindables.BindingFragment
import dagger.hilt.android.AndroidEntryPoint
import io.getstream.avengerschat.R
import io.getstream.avengerschat.components.StreamUIComponent
import io.getstream.avengerschat.components.streamMessageListComponent
import io.getstream.avengerschat.databinding.FragmentLiveStreamBinding
import io.getstream.avengerschat.view.home.HomeViewModel

@AndroidEntryPoint
class LiveStreamFragment :
  BindingFragment<FragmentLiveStreamBinding>(R.layout.fragment_live_stream) {

  private val args: LiveStreamFragmentArgs by navArgs()
  private val homeViewModel: HomeViewModel by activityViewModels()
  private val streamMessageListComponent: StreamUIComponent by streamMessageListComponent(
    cidProvider = { args.info.cid }
  )

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    super.onCreateView(inflater, container, savedInstanceState)
    return binding {
      video = args.info.video
      vm = homeViewModel
    }.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    // initializes and bind layouts to Stream message list UI components.
    streamMessageListComponent.bindLayout(binding.root)
    binding.messageListView.setMessageViewHolderFactory(LiveStreamMessageItemVhFactory.create())
  }

  override fun onResume() {
    super.onResume()
    homeViewModel.visibleBottomNav = false
  }

  override fun onPause() {
    super.onPause()
    homeViewModel.visibleBottomNav = true
  }
}
