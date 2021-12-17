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

package io.getstream.avengerschat.view.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.skydoves.bindables.BindingBottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import io.getstream.avengerschat.R
import io.getstream.avengerschat.databinding.DialogFragmentUserProfileBinding
import io.getstream.avengerschat.view.home.HomeViewModel

@AndroidEntryPoint
class UserProfileDialogFragment :
    BindingBottomSheetDialogFragment<DialogFragmentUserProfileBinding>(R.layout.dialog_fragment_user_profile) {

    private val viewModel: HomeViewModel by activityViewModels()

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
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.edit.setOnClickListener {
            UserProfileEditDialogFragment.create().show(
                requireActivity().supportFragmentManager,
                UserProfileEditDialogFragment.TAG
            )
            dismissAllowingStateLoss()
        }
    }

    companion object {
        const val TAG = "UserInfoDialogFragment"

        fun create() = UserProfileDialogFragment()
    }
}
