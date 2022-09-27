package com.architecturecourse.commandlineinterpreter.entities.utils.error

object FirstTokenError : Throwable("First token contains invalid symbols."), CLIError {
    override val code = 13
}