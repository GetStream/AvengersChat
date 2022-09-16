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

package io.getstream.avengerschat.view.home

import android.os.Bundle
import androidx.activity.viewModels
import com.skydoves.bindables.BindingActivity
import com.skydoves.bundler.bundleNonNull
import com.skydoves.bundler.intentOf
import com.skydoves.transformationlayout.TransformationCompat
import com.skydoves.transformationlayout.TransformationCompat.onTransformationEndContainerApplyParams
import com.skydoves.transformationlayout.TransformationLayout
import dagger.hilt.android.AndroidEntryPoint
import io.getstream.avengerschat.R
import io.getstream.avengerschat.core.model.Avenger
import io.getstream.avengerschat.databinding.ActivityHomeBinding
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : BindingActivity<ActivityHomeBinding>(R.layout.activity_home) {

  @Inject
  internal lateinit var detailViewModelFactory: HomeViewModel.AssistedFactory

  private val viewModel: HomeViewModel by viewModels {
    HomeViewModel.provideFactory(detailViewModelFactory, avenger)
  }

  private val avenger: Avenger by bundleNonNull(EXTRA_AVENGER)

  override fun onCreate(savedInstanceState: Bundle?) {
    onTransformationEndContainerApplyParams(this)
    super.onCreate(savedInstanceState)
    binding {
      fragmentManager = supportFragmentManager
      lifecycleOwner = this@HomeActivity
      vm = viewModel
    }
  }

  companion object {
    private const val EXTRA_AVENGER = "EXTRA_AVENGER"

    fun startActivity(
      transformationLayout: TransformationLayout,
      avenger: Avenger
    ) = transformationLayout.context.intentOf<HomeActivity> {
      putExtra(EXTRA_AVENGER to avenger)
      TransformationCompat.startActivity(transformationLayout, intent)
    }
  }
}
