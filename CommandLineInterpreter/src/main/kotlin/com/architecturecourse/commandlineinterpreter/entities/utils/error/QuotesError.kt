package com.architecturecourse.commandlineinterpreter.entities.utils.error

object QuotesError : Throwable("Quotes not closed."), CLIError {
    override val code = 14
}