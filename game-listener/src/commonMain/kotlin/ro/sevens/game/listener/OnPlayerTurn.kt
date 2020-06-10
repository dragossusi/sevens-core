package ro.sevens.game.listener

import ro.sevens.payload.game.PlayerTurnResponse

interface OnPlayerTurn {
    fun onPlayerTurn(playerTurn: PlayerTurnResponse)
}