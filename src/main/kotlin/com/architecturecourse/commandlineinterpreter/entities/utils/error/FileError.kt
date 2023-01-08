package com.architecturecourse.commandlineinterpreter.entities.utils.error

object FileError : Throwable("Something wrong with file. Make sure it exists."), CLIError {
    override val code = 12
}