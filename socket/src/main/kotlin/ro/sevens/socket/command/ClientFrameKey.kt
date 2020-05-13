package ro.sevens.socket.command

import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import ro.sevens.payload.Card
import ro.sevens.payload.game.NewRoundResponse
import java.lang.reflect.Type

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
enum class ClientFrameKey(
    override val key: String,
    override val type: Type? = null
) : FrameKey {

    @SerializedName("connected_room")
    CONNECTED_ROOM(
        "connected_room",
        Long::class.java
    ),

    @SerializedName("new_round")
    NEW_ROUND(
        "new_round",
        object : TypeToken<NewRoundResponse>() {}.type
    ),

    @SerializedName("player_turn")
    PLAYER_TURN("player_turn"),

    @SerializedName("room_stop")
    ROOM_STOP("room_stop")
}