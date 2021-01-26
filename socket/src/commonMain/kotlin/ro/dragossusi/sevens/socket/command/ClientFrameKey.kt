package ro.dragossusi.sevens.socket.command

import kotlinx.serialization.KSerializer
import kotlinx.serialization.PolymorphicSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.builtins.serializer
import ro.dragossusi.sevens.payload.base.LobbyData
import ro.dragossusi.sevens.payload.database.LobbyResponse
import ro.dragossusi.sevens.payload.game.GameEndResponse
import ro.dragossusi.sevens.payload.game.GameStartedResponse
import ro.dragossusi.sevens.payload.game.NewRoundResponse
import ro.dragossusi.sevens.payload.game.PlayerTurnResponse

/**
 *
 * Frame keys received by the client
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
sealed class ClientFrameKey<T>(
    override val key: String,
    override val serializer: KSerializer<T>?
) : FrameKey<T> {

    override fun equals(other: Any?): Boolean {
        if (other == key) return true
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return key.hashCode()
    }

    override fun toString(): String {
        return key
    }

    companion object {

        /**
         * All types used for socket
         */
        fun values() = arrayOf(
            CONNECTED_LOBBY,
            LOBBY_PLAYER_CONNECTED,
            LOBBY_PLAYER_DISCONNECTED,
            CONNECTED_ROOM,
            NEW_ROUND,
            PLAYER_TURN,
            END_ROUND,
            ROOM_STOP,
            GAME_STARTED,
            GAME_ENDED
        )
    }

    /**
     * The client connected to the lobby
     */
    @SerialName("connected_lobby")
    object CONNECTED_LOBBY : ClientFrameKey<LobbyData>(
        "connected_lobby",
        PolymorphicSerializer(LobbyData::class)
    )

    /**
     * The client connected to the room
     */
    @SerialName("lobby_player_connected")
    object LOBBY_PLAYER_CONNECTED : ClientFrameKey<LobbyData>(
        "lobby_player_connected",
        PolymorphicSerializer(LobbyData::class)
    )

    /**
     * The client connected to the room
     */
    @SerialName("lobby_player_disconnected")
    object LOBBY_PLAYER_DISCONNECTED : ClientFrameKey<LobbyData>(
        "lobby_player_disconnected",
        PolymorphicSerializer(LobbyData::class)
    )

    /**
     * The client connected to the room
     */
    @SerialName("connected_room")
    object CONNECTED_ROOM : ClientFrameKey<Long>(
        "connected_room",
        Long.serializer()
    )

    /**
     * A new round started
     */
    @SerialName("new_round")
    object NEW_ROUND : ClientFrameKey<NewRoundResponse>("new_round", NewRoundResponse.serializer())

    /**
     * The game started
     */
    @SerialName("game_started")
    object GAME_STARTED :
        ClientFrameKey<GameStartedResponse>("game_started", GameStartedResponse.serializer())

    /**
     * The game ended
     */
    @SerialName("game_ended")
    object GAME_ENDED : ClientFrameKey<GameEndResponse>("game_ended", GameEndResponse.serializer())

    /**
     * The round ended
     */
    @SerialName("end_round")
    object END_ROUND : ClientFrameKey<NewRoundResponse>("end_round", NewRoundResponse.serializer())

    /**
     * It's a new player's turn
     */
    @SerialName("player_turn")
    object PLAYER_TURN : ClientFrameKey<PlayerTurnResponse>(
        "player_turn", PlayerTurnResponse.serializer()
    )

    /**
     * Room stopped
     */
    @SerialName("room_stop")
    object ROOM_STOP : ClientFrameKey<Nothing>("room_stop", null)
}