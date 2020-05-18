package ro.sevens.logger

import java.util.logging.Level
import java.util.logging.Logger

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
abstract class JavaLogger(
    private val logger: Logger
) : TagLogger {

    override fun d(message: String) {
        logger.log(Level.INFO, message)
    }

    override fun e(message: String) {
        logger.log(Level.SEVERE, message)
    }

    override fun e(throwable: Throwable) {
        logger.log(Level.SEVERE, throwable.message ?: "Error", throwable)
    }

    override fun w(message: String) {
        logger.log(Level.WARNING, message)
    }

}