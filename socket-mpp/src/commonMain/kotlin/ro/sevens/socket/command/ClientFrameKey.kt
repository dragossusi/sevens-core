package ro.sevens.socket.command

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.builtins.serializer
import ro.sevens.payload.game.NewRoundResponse
import ro.sevens.payload.game.PlayerTurnResponse

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
sealed class ClientFrameKey<T>(
    override val key: String,
    override val serializer: KSerializer<T>?
) : FrameKey<T> {

    override fun equals(other: Any?): Boolean {
        if (other == key) return true
        return super.equals(other)
    }

    override fun toString(): String {
        return key
    }

    companion object {
        fun values() = arrayOf(
            CONNECTED_ROOM,
            NEW_ROUND,
            PLAYER_TURN,
            ROOM_STOP
        )
    }

    @SerialName("connected_room")
    object CONNECTED_ROOM : ClientFrameKey<Long>(
        "connected_room",
        Long.serializer()
    )

    @SerialName("new_round")
    object NEW_ROUND : ClientFrameKey<NewRoundResponse>("new_round", NewRoundResponse.serializer())

    @SerialName("end_round")
    object END_ROUND : ClientFrameKey<NewRoundResponse>("end_round", NewRoundResponse.serializer())

    @SerialName("player_turn")
    object PLAYER_TURN : ClientFrameKey<PlayerTurnResponse>(
        "player_turn", PlayerTurnResponse.serializer()
    )

    @SerialName("room_stop")
    object ROOM_STOP : ClientFrameKey<Nothing>("room_stop", null)
}