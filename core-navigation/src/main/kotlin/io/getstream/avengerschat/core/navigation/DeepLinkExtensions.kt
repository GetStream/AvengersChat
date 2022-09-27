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

package io.getstream.avengerschat.core.navigation

import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController

fun Fragment.navigateToLive() {
  findNavController().navigate(createDeepLinkRequest(getString(R.string.deeplink_live)))
}

fun Fragment.navigateToMessageList(
  cid: String,
  messageId: String = ""
) {
  findNavController().navigate(
    createDeepLinkRequest(
      "android-app://io.getstream.avengerschat/message_list?cid=$cid&messageId=$messageId"
    )
  )
}

private fun createDeepLinkRequest(link: String): NavDeepLinkRequest {
  return NavDeepLinkRequest.Builder
    .fromUri(link.toUri())
    .build()
}
