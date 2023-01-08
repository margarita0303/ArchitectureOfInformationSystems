package com.architecturecourse.commandlineinterpreter.entities.utils.error

object EmptyInputError : Throwable("Empty input."), CLIError {
    override val code = 10
}