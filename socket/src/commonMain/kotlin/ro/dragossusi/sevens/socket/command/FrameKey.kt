package ro.dragossusi.sevens.socket.command

import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import ro.dragossusi.sevens.socket.CommandFrame

/**
 *
 * Frame keys used for deserialization
 *
 * @param key the name used in the response
 * @param serializer the serializer used for the value received in response, null for no value
 *
 * server
 *
 * Copyright (C) 2020  Rachieru Dragos-Mihai
 *
 * server is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 *
 * server is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with server.  If not, see [License](http://www.gnu.org/licenses/) .
 *
 */
interface FrameKey<T> {
    /**
     * the name used in the response
     */
    val key: String

    /**
     * the serializer used for the value received in response, null for no value
     */
    val serializer: KSerializer<T>?

}

/**
 * Deserialize json to value type
 *
 * @param json configuration used
 * @param valueString text to deserialize
 *
 */
fun <T> FrameKey<T>.read(
    json: Json,
    valueString: String?
): T? {
    return valueString?.let { string ->
        serializer?.let {
            json.decodeFromString(it, string)
        }
    }
}

/**
 * Transform value string into a CommandFrame
 */
fun <T> FrameKey<T>.toCommandFrame(json: Json, jsonString: String?): CommandFrame<T> {
    return CommandFrame(
        this,
        read(json, jsonString)
    )
}