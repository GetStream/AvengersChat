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

package io.stream.avengerschat.view.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.skydoves.bindables.BindingBottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import io.stream.avengerschat.R
import io.stream.avengerschat.databinding.DialogFragmentUserProfileEditBinding
import io.stream.avengerschat.extensions.doOnUrlTextChanged
import io.stream.avengerschat.view.home.HomeViewModel

@AndroidEntryPoint
class UserProfileEditDialogFragment :
    BindingBottomSheetDialogFragment<DialogFragmentUserProfileEditBinding>(R.layout.dialog_fragment_user_profile_edit) {

    private val homeViewModel: HomeViewModel by activityViewModels()
    private val editViewModel: UserProfileEditViewModel by viewModels()

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
            vm = editViewModel
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding {
            profileEditText.doOnUrlTextChanged {
                editViewModel.profileUrl = it
            }
            enter.setOnClickListener {
                editViewModel.profileUrl = null
                profileEditText.text?.clear()
            }
        }
    }

    companion object {
        const val TAG = "UserInfoDialogEditFragment"

        fun create() = UserProfileEditDialogFragment()
    }
}
