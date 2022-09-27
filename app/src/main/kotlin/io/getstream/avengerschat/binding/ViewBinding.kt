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

package io.getstream.avengerschat.binding

import android.app.Activity
import android.view.View
import android.view.WindowManager
import androidx.databinding.BindingAdapter
import com.yarolegovich.discretescrollview.DiscreteScrollView
import com.yarolegovich.discretescrollview.transform.ScaleTransformer
import io.getstream.avengerschat.core.data.extensions.parsedColor
import io.getstream.avengerschat.core.model.Avenger
import io.getstream.avengerschat.core.uicomponents.StreamGlobalStyles
import io.getstream.avengerschat.core.uicomponents.extensions.adapterPositionOrNull
import io.getstream.avengerschat.core.uicomponents.extensions.startCircularReveal
import io.getstream.avengerschat.view.main.MainAvengersAdapter

object ViewBinding {
  @JvmStatic
  @BindingAdapter("adapterAvengers")
  fun bindAdapterPosterList(view: DiscreteScrollView, posters: List<Avenger>?) {
    (view.adapter as? MainAvengersAdapter)?.submitList(posters)
    view.setItemTransformer(
      ScaleTransformer.Builder()
        .setMaxScale(1.25f)
        .setMinScale(0.8f)
        .build()
    )
  }

  @JvmStatic
  @BindingAdapter("bindOnItemChanged", "bindOnItemChangedBackground")
  fun bindOnItemChanged(view: DiscreteScrollView, adapter: MainAvengersAdapter, pointView: View) {
    view.addOnItemChangedListener { viewHolder, _ ->
      val position = viewHolder?.adapterPositionOrNull ?: return@addOnItemChangedListener
      if (position >= 0 && position < adapter.itemCount) {
        val parsedColor = adapter.getAvenger(position).parsedColor
        bindStatusBarColor(view, parsedColor)
        pointView.startCircularReveal(parsedColor)

        // updates global styles of the stream chat lists.
        StreamGlobalStyles.updatePrimaryColorGlobalStyles(parsedColor)
      }
    }
  }

  private fun bindStatusBarColor(
    view: View,
    color: Int
  ) {
    val context = view.context
    if (context is Activity) {
      val window = context.window
      window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
      window.statusBarColor = color
    }
  }
}
