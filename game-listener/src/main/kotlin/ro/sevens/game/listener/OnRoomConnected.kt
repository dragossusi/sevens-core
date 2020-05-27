package ro.sevens.game.listener

interface OnRoomConnected {
    suspend fun onRoomConnected(id: Long)
}