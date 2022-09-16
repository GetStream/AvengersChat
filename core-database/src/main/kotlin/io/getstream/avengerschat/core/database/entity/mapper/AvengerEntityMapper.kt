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

package io.getstream.avengerschat.core.database.entity.mapper

import io.getstream.avengerschat.core.database.entity.AvengerEntity
import io.getstream.avengerschat.core.model.Avenger

object AvengerEntityMapper : EntityMapper<Avenger, AvengerEntity> {

  override fun asEntity(domain: Avenger): AvengerEntity {
    return AvengerEntity(
      id = domain.id,
      name = domain.name,
      token = domain.token,
      color = domain.color,
      quote = domain.quote,
      video = domain.video,
      livecid = domain.livecid,
      poster = domain.poster
    )
  }

  override fun asDomain(entity: AvengerEntity): Avenger {
    return Avenger(
      id = entity.id,
      name = entity.name,
      token = entity.token,
      color = entity.color,
      quote = entity.quote,
      video = entity.video,
      livecid = entity.livecid,
      poster = entity.poster
    )
  }
}

fun Avenger.asEntity(): AvengerEntity {
  return AvengerEntityMapper.asEntity(this)
}

fun AvengerEntity.asDomain(): Avenger {
  return AvengerEntityMapper.asDomain(this)
}
