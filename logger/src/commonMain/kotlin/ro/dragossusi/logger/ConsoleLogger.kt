package ro.dragossusi.logger

/**
 * sevens-client
 *
 * Copyright (C) 2020  Rachieru Dragos-Mihai
 *
 * sevens-client is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 *
 * sevens-client is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with sevens-client.  If not, see [License](http://www.gnu.org/licenses/) .
 *
 */
open class ConsoleLogger(private val tag: String) : TagLogger {

    override fun i(message: String) {
        println("$tag/I: $message")
    }

    override fun d(message: String) {
        println("$tag/D: $message")
    }

    override fun e(message: String) {
        println("$tag/E: $message")
    }

    override fun e(throwable: Throwable) {
        println("$tag/E: $throwable")
    }

    override fun w(message: String) {
        println("$tag/W: $message")
    }

}