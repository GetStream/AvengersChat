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

package io.stream.avengerschat.view.main.you

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.skydoves.bindables.BindingBottomSheetDialogFragment
import com.skydoves.bundler.bundleNonNull
import dagger.hilt.android.AndroidEntryPoint
import io.stream.avengerschat.R
import io.stream.avengerschat.databinding.DialogFragmentYouBinding
import io.stream.avengerschat.extensions.isValidForId
import io.stream.avengerschat.model.Avenger
import io.stream.avengerschat.view.home.HomeActivity

@AndroidEntryPoint
class YouDialogFragment :
    BindingBottomSheetDialogFragment<DialogFragmentYouBinding>(R.layout.dialog_fragment_you) {

    private val viewModel: YouViewModel by viewModels()
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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.join.setOnClickListener {
            val name = binding.nameEditText.text.toString()
            if (name.isValidForId) {
                val newAvenger =
                    viewModel.createGuestAvenger(avenger, name, getString(R.string.greeting, name))
                HomeActivity.startActivity(binding.transformationLayout, newAvenger)
                dismissAllowingStateLoss()
            }
        }
    }

    companion object {
        const val TAG = "YouDialogFragment"
        private const val EXTRA_AVENGER = "EXTRA_AVENGER"

        fun create(avenger: Avenger) = YouDialogFragment().apply {
            arguments = bundleOf(EXTRA_AVENGER to avenger)
        }
    }
}
