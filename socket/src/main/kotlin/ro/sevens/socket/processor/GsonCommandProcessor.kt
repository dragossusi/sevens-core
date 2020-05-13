package ro.sevens.socket.processor

import com.google.gson.Gson
import ro.sevens.socket.CommandFrame
import ro.sevens.socket.SocketCommandLogger
import ro.sevens.socket.command.FrameKey
import ro.sevens.socket.command.RawCommand
import java.util.*

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
class GsonCommandProcessor(
    val gson: Gson,
    val inFrameKeyValues: Array<out FrameKey>,
    val outFrameKeyValues: Array<out FrameKey>
) : CommandProcessor {

    override fun <T> process(command: CommandFrame<T>): String {
        return "${command.key}:${gson.toJson(command.data)}"
    }

    override fun readIn(frameText: String): CommandFrame<*>? {
        val raw = readRaw(frameText)
        inFrameKeyValues.forEach {
            if (it.key == raw.key) {
                return CommandFrame(
                    it,
                    it.type?.let {
                        gson.fromJson<Any>(raw.json, it)
                    }
                )
            }
        }
        return null
    }

    override fun readRaw(frameText: String): RawCommand {
        SocketCommandLogger.d("received : $frameText")
        val delimiterIndex = frameText.indexOf(':')
        val key = if (delimiterIndex == -1) frameText else frameText.substring(0, delimiterIndex)
        val json = if (delimiterIndex == -1) null else frameText.substring(delimiterIndex + 1)
        return RawCommand(key.toLowerCase(Locale.ENGLISH), json)
    }
}