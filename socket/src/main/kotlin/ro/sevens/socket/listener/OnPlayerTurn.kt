package ro.sevens.socket.listener

import ro.sevens.payload.game.PlayerTurnResponse

interface OnPlayerTurn {
    suspend fun onPlayerTurn(playerTurn: PlayerTurnResponse)
}