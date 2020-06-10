package ro.sevens.socket.processor

import kotlinx.serialization.json.Json
import ro.sevens.logger.TagLogger
import ro.sevens.socket.CommandFrame
import ro.sevens.socket.command.*

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
open class JsonCommandProcessor(
        private val json: Json,
        private val tagLogger: TagLogger
) : CommandProcessor {
    private val inFrameKeyValues: Array<out FrameKey<*>> = ClientFrameKey.values()
    private val outFrameKeyValues: Array<out FrameKey<*>> = ServerFrameKey.values()

    override fun <T> process(command: CommandFrame<T>): String {
        return "${command.key.key}:${command.toJson(json)}"
    }

    override fun readIn(frameText: String): CommandFrame<*>? {
        val raw = readRaw(frameText)
        inFrameKeyValues.forEach { frame ->
            if (frame.key == raw.key) {
                return frame.toCommandFrame(json, raw.json)
            }
        }
        return null
    }

    override fun readRaw(frameText: String): RawCommand {
        tagLogger.d("received : $frameText")
        val delimiterIndex = frameText.indexOf(':')
        val key = if (delimiterIndex == -1) frameText else frameText.substring(0, delimiterIndex)
        val json = if (delimiterIndex == -1) null else frameText.substring(delimiterIndex + 1)
        return RawCommand(key.toLowerCase(), json)
    }

}