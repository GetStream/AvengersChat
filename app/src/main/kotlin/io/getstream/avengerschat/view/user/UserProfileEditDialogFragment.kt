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

package io.getstream.avengerschat.view.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.skydoves.bindables.BindingBottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import io.getstream.avengerschat.R
import io.getstream.avengerschat.databinding.DialogFragmentUserProfileEditBinding
import io.getstream.avengerschat.extensions.doOnUrlTextChanged
import io.getstream.avengerschat.extensions.hideSoftInputFromWindow
import io.getstream.avengerschat.extensions.toast
import io.getstream.avengerschat.view.home.HomeViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class UserProfileEditDialogFragment :
  BindingBottomSheetDialogFragment<DialogFragmentUserProfileEditBinding>(R.layout.dialog_fragment_user_profile_edit) {

  private val homeViewModel: HomeViewModel by activityViewModels()

  @Inject
  internal lateinit var editModelFactory: UserProfileEditViewModel.AssistedFactory
  private val editViewModel: UserProfileEditViewModel by viewModels {
    UserProfileEditViewModel.provideFactory(editModelFactory, homeViewModel.avenger)
  }

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
      homeVm = homeViewModel
      vm = editViewModel
    }.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding {
      profileEditText.doOnUrlTextChanged {
        editViewModel.sendEnabled = it
      }

      enter.setOnClickListener {
        root.hideSoftInputFromWindow()
        val editable = profileEditText.text
        editViewModel.profileUrl = editable.toString()
        editable?.clear()
      }

      update.setOnClickListener {
        editViewModel.sendUrl()
      }
    }

    lifecycleScope.launch {
      editViewModel.updatedUser.collect {
        requireContext().toast(R.string.edit_profile_updated)
        dismissAllowingStateLoss()
      }
    }
  }

  companion object {
    const val TAG = "UserInfoDialogEditFragment"

    fun create() = UserProfileEditDialogFragment()
  }
}
