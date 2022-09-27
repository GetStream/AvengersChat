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

package io.getstream.avengerschat.feature.home.binding

import android.app.Activity
import android.view.View
import android.view.WindowManager
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.getstream.avengerschat.core.uicomponents.extensions.color
import io.getstream.avengerschat.core.uicomponents.extensions.setBadgeNumber
import io.getstream.avengerschat.feature.home.R

internal object ViewBinding {

  @JvmStatic
  @BindingAdapter("totalUnreadCount")
  fun bindTotalUnreadCount(bottomNavigationView: BottomNavigationView, totalUnreadCount: Int) {
    bottomNavigationView.setBadgeNumber(R.id.fragment_channel_list, totalUnreadCount)
  }

  @JvmStatic
  @BindingAdapter("navigation")
  fun bindNavigation(
    bottomNavigationView: BottomNavigationView,
    fragmentManager: FragmentManager
  ) {
    val controller =
      (fragmentManager.findFragmentById(R.id.container) as NavHostFragment).navController
    bottomNavigationView.apply {
      setOnItemSelectedListener {
        val deeplink = when (it.itemId) {
          R.id.menu_live -> "android-app://io.getstream.avengerschat/live"
          R.id.menu_channel_list -> "android-app://io.getstream.avengerschat/channel_list"
          R.id.menu_mentions -> "android-app://io.getstream.avengerschat/mentions"
          else -> ""
        }
        val request = NavDeepLinkRequest.Builder.fromUri(deeplink.toUri()).build()
        controller.navigate(request)
        true
      }
      val context = bottomNavigationView.context
      val accentRed = context.color(R.color.stream_ui_accent_red)
      val literalWhite =
        context.color(R.color.stream_ui_literal_white)
      getOrCreateBadge(R.id.fragment_channel_list).apply {
        backgroundColor = accentRed
        badgeTextColor = literalWhite
      }
    }
  }

  @JvmStatic
  @BindingAdapter("statusBarColor")
  fun bindStatusBarColor(
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
