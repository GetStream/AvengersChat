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

package io.stream.avengerschat.binding

import android.app.Activity
import android.view.View
import android.view.WindowManager
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import coil.imageLoader
import coil.load
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.skydoves.androidveil.VeilLayout
import io.getstream.chat.android.client.models.User
import io.getstream.chat.android.ui.avatar.AvatarView
import io.getstream.chat.android.ui.channel.list.header.ChannelListHeaderView
import io.stream.avengerschat.R
import io.stream.avengerschat.extensions.color
import io.stream.avengerschat.extensions.setBadgeNumber

object ViewBinding {
    @JvmStatic
    @BindingAdapter("isGone")
    fun bindIsGone(view: View, isGone: Boolean) {
        view.isVisible = !isGone
    }

    @JvmStatic
    @BindingAdapter("loadImage")
    fun bindLoadImage(view: AppCompatImageView, url: String?) {
        view.load(url)
    }

    @JvmStatic
    @BindingAdapter("loadCircleImage")
    fun bindLoadCircleImage(view: AppCompatImageView, url: String?) {
        val request = ImageRequest.Builder(view.context)
            .data(url)
            .target(view)
            .transformations(CircleCropTransformation())
            .lifecycle(view.findViewTreeLifecycleOwner())
            .build()
        view.context.imageLoader.enqueue(request)
    }

    @JvmStatic
    @BindingAdapter("withVeil", "loadImageWithVeil")
    fun bindLoadImageWithVeil(view: AppCompatImageView, veilLayout: VeilLayout, url: String?) {
        val request = ImageRequest.Builder(view.context)
            .data(url)
            .target(view)
            .lifecycle(view.findViewTreeLifecycleOwner())
            .listener(
                onCancel = { veilLayout.unVeil() },
                onError = { _, _ -> veilLayout.unVeil() },
                onSuccess = { _, _ -> veilLayout.unVeil() }
            )
            .build()
        view.context.imageLoader.enqueue(request)
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
            val accentRed = context.color(io.getstream.chat.android.ui.R.color.stream_ui_accent_red)
            val literalWhite =
                context.color(io.getstream.chat.android.ui.R.color.stream_ui_literal_white)
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
    @BindingAdapter("onlineIndicator")
    fun bindOnlineIndicator(avatarView: AvatarView, user: User?) {
        user?.let { avatarView.setUserData(it) }
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
}
