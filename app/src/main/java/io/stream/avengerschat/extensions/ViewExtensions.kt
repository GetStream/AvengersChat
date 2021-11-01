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

import android.view.View
import android.view.ViewAnimationUtils
import android.webkit.URLUtil
import androidx.annotation.ColorInt
import androidx.annotation.IdRes
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.stream.avengerschat.R
import kotlin.math.hypot

fun View.startCircularReveal(@ColorInt color: Int) {
    val cx = 0
    val cy = left
    val finalRadius = hypot(width.toDouble(), height.toDouble())
    if (isAttachedToWindow) {
        ViewAnimationUtils.createCircularReveal(
            this, cx, cy, 0f, finalRadius.toFloat()
        ).apply {
            DrawableCompat.setTint(background, color)
            isVisible = true
            duration = 450
            start()
        }
    }
}

fun BottomNavigationView.setBadgeNumber(@IdRes menuItemId: Int, badgeNumber: Int) {
    getOrCreateBadge(menuItemId).apply {
        horizontalOffset = -context.dimensionPixelSize(R.dimen.badge_horizontal_offset)
        verticalOffset = context.dimensionPixelSize(R.dimen.badge_vertical_offset)
        backgroundColor = context.color(io.getstream.chat.android.ui.R.color.stream_ui_accent_red)
        isVisible = badgeNumber > 0
        number = badgeNumber
    }
}

inline fun AppCompatEditText.doOnUrlTextChanged(crossinline block: (String) -> Unit) {
    doAfterTextChanged {
        val url = it.toString()
        if (URLUtil.isNetworkUrl(url)) {
            block(url)
        }
    }
}
