package ro.sevens.logger

interface TagLogger {
    fun d(message: String)
    fun e(message: String)
    fun e(throwable: Throwable)
    fun w(message: String)
}