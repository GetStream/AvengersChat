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

package io.getstream.avengerschat.core.data.repository.home

import androidx.annotation.WorkerThread
import io.getstream.avengerschat.core.model.Avenger
import io.getstream.avengerschat.core.model.LiveRoomInfo
import io.getstream.chat.android.client.models.ConnectionData
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

  @WorkerThread
  fun connectUser(avenger: Avenger): Flow<ConnectionData>

  fun disconnectUser(avenger: Avenger)

  @WorkerThread
  fun getLiveRoomInfo(avenger: Avenger): Flow<List<LiveRoomInfo>>
}
