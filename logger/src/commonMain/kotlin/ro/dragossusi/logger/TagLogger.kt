package ro.dragossusi.logger

interface TagLogger {
    fun i(message: String)
    fun d(message: String)
    fun e(message: String)
    fun e(throwable: Throwable)
    fun w(message: String)
}