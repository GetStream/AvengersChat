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

package io.stream.avengerschat.extensions

import android.content.Context
import android.graphics.Color
import android.text.format.DateUtils
import com.getstream.sdk.chat.adapter.MessageListItem
import com.getstream.sdk.chat.utils.DateFormatter
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.models.User
import io.stream.avengerschat.R
import io.stream.avengerschat.model.Avenger
import io.stream.avengerschat.model.LiveRoomInfo
import org.threeten.bp.Instant
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId
import java.util.Date

@PublishedApi
internal const val EXTRA_NAME = "name"

@PublishedApi
internal const val EXTRA_IMAGE = "image"

@PublishedApi
internal const val EXTRA_TEAM = "team"

@PublishedApi
internal const val emptyString: String = ""

val Avenger.extraData: MutableMap<String, Any>
    inline get() = mutableMapOf(
        EXTRA_NAME to name
    )

fun Avenger.extraData(newProfileUrl: String): MutableMap<String, Any> {
    return mutableMapOf(
        EXTRA_NAME to name,
        EXTRA_IMAGE to newProfileUrl
    )
}

val Avenger.parsedColor: Int
    inline get() = Color.parseColor(color)

val Avenger.liveRoomInfo: LiveRoomInfo
    inline get() = LiveRoomInfo(cid = livecid, video = video)

fun MessageListItem.MessageItem.localDate(context: Context): String {
    val formatter = DateFormatter.from(context)
    val date = message.createdAt ?: message.createdLocallyAt
    return formatter.formatDate(date?.let(::toLocalDateTime))
}

private fun toLocalDateTime(date: Date): LocalDateTime {
    return Instant.ofEpochMilli(date.time)
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime()
}

fun User.lastActive(context: Context): String? =
    lastActive?.let {
        context.getString(
            R.string.stream_channel_header_active,
            when {
                it.isInLastMinute() -> context.getString(R.string.stream_channel_header_active_now)
                else -> DateUtils.getRelativeTimeSpanString(it.time).toString()
            }
        )
    }

private fun Date.isInLastMinute(): Boolean = (Date().time - 60000 < time)

val ChatClient.currentUserId
    inline get() = getCurrentUser()?.id ?: emptyString
