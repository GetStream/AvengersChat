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
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import io.getstream.avengerschat.R
import io.getstream.avengerschat.core.uicomponents.extensions.color
import io.getstream.avengerschat.core.uicomponents.extensions.drawable
import io.getstream.avengerschat.core.uicomponents.extensions.setBadgeNumber
import io.getstream.chat.android.client.models.User
import io.getstream.chat.android.ui.avatar.AvatarView
import io.getstream.chat.android.ui.channel.list.header.ChannelListHeaderView

object ViewBinding {
  @JvmStatic
  @BindingAdapter("isGone")
  fun bindIsGone(view: View, isGone: Boolean) {
    view.isVisible = !isGone
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
      setupWithNavController(controller)
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
  @BindingAdapter("totalUnreadCount")
  fun bindTotalUnreadCount(bottomNavigationView: BottomNavigationView, totalUnreadCount: Int) {
    bottomNavigationView.setBadgeNumber(R.id.fragment_channel_list, totalUnreadCount)
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

  @JvmStatic
  @BindingAdapter("channelListHeader")
  fun bindChannelListHeader(channelListHeaderView: ChannelListHeaderView, user: User?) {
    if (user != null) {
      channelListHeaderView.apply {
        setUser(user)
        showOnlineTitle()
        setOnlineTitle(context.getString(R.string.app_name))
      }
    }
  }

  @JvmStatic
  @BindingAdapter("playYoutubeVideo")
  fun bindPlayYoutubeVideo(youTubePlayerView: YouTubePlayerView, videoId: String) {
    val playListener = object : AbstractYouTubePlayerListener() {
      override fun onReady(youTubePlayer: YouTubePlayer) {
        youTubePlayer.loadVideo(videoId = videoId, startSeconds = 0f)
      }
    }

    val playerOptions = IFramePlayerOptions.Builder()
      .controls(0)
      .rel(0)
      .build()

    youTubePlayerView.initialize(playListener, false, playerOptions)
  }

  @JvmStatic
  @BindingAdapter("user")
  fun bindUser(avatarView: AvatarView, user: User?) {
    user?.let { avatarView.setUserData(it) }
  }

  @JvmStatic
  @BindingAdapter("sendUrlBtn")
  fun bindSendUrlButton(imageButton: AppCompatImageButton, enabled: Boolean) {
    val context = imageButton.context
    imageButton.isEnabled = enabled
    imageButton.background = if (enabled) {
      context.drawable(R.drawable.shape_circle)
    } else {
      context.drawable(R.drawable.shape_circle_disabled)
    }
  }
}
