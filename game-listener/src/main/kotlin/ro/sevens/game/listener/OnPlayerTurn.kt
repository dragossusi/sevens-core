package ro.sevens.game.listener

import ro.sevens.payload.game.PlayerTurnResponse

interface OnPlayerTurn {
    suspend fun onPlayerTurn(playerTurn: PlayerTurnResponse)
}