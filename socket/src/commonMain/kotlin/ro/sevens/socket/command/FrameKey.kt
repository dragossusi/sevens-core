package ro.sevens.socket.command

import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.parse
import ro.sevens.socket.CommandFrame

/**
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
    val key: String
    val serializer: KSerializer<T>?

}

fun <T> FrameKey<T>.read(json: Json, jsonString: String?): T? {
    return jsonString?.let { string ->
        serializer?.let {
            json.decodeFromString(it, string)
        }
    }
}

fun <T> FrameKey<T>.toCommandFrame(json: Json, jsonString: String?): CommandFrame<T> {
    return CommandFrame(
        this,
        read(json, jsonString)
    )
}