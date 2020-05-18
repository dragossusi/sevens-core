package ro.sevens.socket.listener

interface OnRoomConnected {
    suspend fun onRoomConnected(id: Long)
}