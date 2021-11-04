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

package io.stream.avengerschat.view.main

import android.os.Bundle
import androidx.activity.viewModels
import com.skydoves.bindables.BindingActivity
import com.skydoves.transformationlayout.onTransformationStartContainer
import dagger.hilt.android.AndroidEntryPoint
import io.stream.avengerschat.R
import io.stream.avengerschat.databinding.ActivityMainBinding
import io.stream.avengerschat.model.Avenger
import io.stream.avengerschat.view.main.you.YouDialogFragment

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        onTransformationStartContainer()
        super.onCreate(savedInstanceState)
        binding {
            adapter = MainAvengersAdapter(onItemYouClicked)
            vm = viewModel
        }
    }

    private val onItemYouClicked: (Avenger) -> Unit = {
        YouDialogFragment.create(it).show(supportFragmentManager, YouDialogFragment.TAG)
    }
}
