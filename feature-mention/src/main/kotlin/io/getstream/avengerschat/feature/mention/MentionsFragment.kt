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

package io.getstream.avengerschat.feature.mention

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import com.skydoves.bindables.BindingFragment
import dagger.hilt.android.AndroidEntryPoint
import io.getstream.avengerschat.feature.mention.databinding.FragmentMentionsBinding
import io.getstream.chat.android.ui.mention.list.viewmodel.MentionListViewModel
import io.getstream.chat.android.ui.mention.list.viewmodel.bindView

@AndroidEntryPoint
class MentionsFragment : BindingFragment<FragmentMentionsBinding>(R.layout.fragment_mentions) {

  private val viewModel: MentionListViewModel by viewModels()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    super.onCreateView(inflater, container, savedInstanceState)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding {
      // initializes and bind layouts to Stream mention list UI components.
      viewModel.bindView(mentionsListView, viewLifecycleOwner)

      mentionsListView.setMentionSelectedListener { message ->
        val request = NavDeepLinkRequest.Builder
          .fromUri("android-app://io.getstream.avengerschat/message_list?cid=${message.cid}&messageId={${message.id}}".toUri())
          .build()
        findNavController().navigate(request = request)
      }
    }
  }
}
