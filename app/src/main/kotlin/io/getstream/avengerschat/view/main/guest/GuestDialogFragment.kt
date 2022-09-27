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

package io.getstream.avengerschat.view.main.guest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.skydoves.bindables.BindingBottomSheetDialogFragment
import com.skydoves.bundler.bundleNonNull
import dagger.hilt.android.AndroidEntryPoint
import io.getstream.avengerschat.R
import io.getstream.avengerschat.core.model.Avenger
import io.getstream.avengerschat.core.uicomponents.extensions.toast
import io.getstream.avengerschat.databinding.DialogFragmentGuestBinding
import io.getstream.avengerschat.extensions.isValidForId
import io.getstream.avengerschat.feature.home.HomeActivity
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GuestDialogFragment :
  BindingBottomSheetDialogFragment<DialogFragmentGuestBinding>(R.layout.dialog_fragment_guest) {

  private val viewModel: GuestViewModel by viewModels()
  private val avenger: Avenger by bundleNonNull(EXTRA_AVENGER)

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
      vm = viewModel
    }.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding.join.setOnClickListener {
      val name = binding.nameEditText.text.toString()
      if (name.isValidForId) {
        viewModel.submitName(name)
      } else {
        requireContext().toast(R.string.enter_edit_your_name_error)
      }
    }

    lifecycleScope.launch {
      viewModel.tokenFlow.collect {
        val newAvenger = viewModel.createGuestAvenger(
          avenger = avenger,
          quote = getString(R.string.greeting),
          token = it
        )
        HomeActivity.startActivity(binding.transformationLayout, newAvenger)
        dismissAllowingStateLoss()
      }
    }
  }

  companion object {
    const val TAG = "GuestRepository"
    private const val EXTRA_AVENGER = "EXTRA_AVENGER"

    fun create(avenger: Avenger) = GuestDialogFragment().apply {
      arguments = bundleOf(EXTRA_AVENGER to avenger)
    }
  }
}
