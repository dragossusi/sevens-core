package ro.dragossusi.sevens.socket.command

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import ro.dragossusi.sevens.payload.Card

/**
 * Frame keys received by the server
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
sealed class ServerFrameKey<T>(
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

        /**
         * All types used for socket
         */
        fun values() = arrayOf(
            PLACE_CARD,
            DRAW_CARD,
            PICK_TYPE,
            END_ROUND
        )
    }

    /**
     * Place a card
     */
    @SerialName("place_card")
    object PLACE_CARD : ServerFrameKey<Card>("place_card", Card.serializer())

    /**
     * Draw a card
     */
    @SerialName("draw_card")
    object DRAW_CARD : ServerFrameKey<Nothing>("draw_card", null)

    /**
     * Pick a card type
     */
    @SerialName("pick_type")
    object PICK_TYPE : ServerFrameKey<Card.Type>("pick_type", Card.Type.serializer())

    /**
     * End a round
     */
    @SerialName("end_round")
    object END_ROUND : ServerFrameKey<Nothing>("end_round", null)

}