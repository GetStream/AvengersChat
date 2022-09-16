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

package io.getstream.avengerschat.core.uicomponents.extensions

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat

fun Context.color(@ColorRes resource: Int): Int {
  return ContextCompat.getColor(this, resource)
}

fun Context.dimensionPixelSize(@DimenRes id: Int): Int {
  return resources.getDimensionPixelSize(id)
}

fun Context.drawable(@DrawableRes resource: Int): Drawable {
  return ResourcesCompat.getDrawable(resources, resource, null)!!
}

fun Context.getInputMethodManager() =
  getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

fun Context.toast(@StringRes resource: Int) =
  Toast.makeText(this, resource, Toast.LENGTH_SHORT).show()
