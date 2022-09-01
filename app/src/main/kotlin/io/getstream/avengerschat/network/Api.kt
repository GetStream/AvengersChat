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

package io.getstream.avengerschat.network

object Api {
    private const val YOUTUBE_THUMBNAIL_URL = "https://img.youtube.com/vi/"

    const val STREAM_USER_ID = "id"
    const val STREAM_USER_ROLE = "role"
    const val STREAM_USER_ROLE_ADMIN = "admin"
    const val STREAM_CHANNEL_TYPE_MESSAGING = "messaging"

    @JvmStatic
    fun getYoutubeThumbnailPath(thumbnailPath: String?): String {
        return "$YOUTUBE_THUMBNAIL_URL$thumbnailPath/default.jpg"
    }
}
