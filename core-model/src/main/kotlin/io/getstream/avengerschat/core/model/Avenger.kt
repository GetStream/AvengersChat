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

package io.getstream.avengerschat.core.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import io.getstream.chat.android.client.ChatClient
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Avenger(
  @field:Json(name = "id") val id: String,
  @field:Json(name = "name") val name: String,
  @field:Json(name = "token") val token: String,
  @field:Json(name = "color") val color: String,
  @field:Json(name = "quote") val quote: String,
  @field:Json(name = "video") val video: String,
  @field:Json(name = "livecid") val livecid: String,
  @field:Json(name = "poster") val poster: String
) : Parcelable {

  fun getProfileImage(): String {
    val currentUser = ChatClient.instance().getCurrentUser()
    if (id == currentUser?.id) {
      return currentUser.image
    }
    return "https://getstream.imgix.net/images/random_svg/${name.first().uppercase()}.png"
  }
}
