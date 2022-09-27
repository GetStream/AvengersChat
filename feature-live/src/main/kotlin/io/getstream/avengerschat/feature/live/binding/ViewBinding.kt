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

package io.getstream.avengerschat.feature.live.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import io.getstream.avengerschat.core.model.LiveRoomInfo

internal object ViewBinding {

  @JvmStatic
  @BindingAdapter("adapterLiveInfo")
  fun bindAdapterLiveRoomInfo(view: RecyclerView, roomInfo: List<LiveRoomInfo>?) {
    (view.adapter as? io.getstream.avengerschat.feature.live.LiveAdapter)?.submitList(roomInfo)
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
