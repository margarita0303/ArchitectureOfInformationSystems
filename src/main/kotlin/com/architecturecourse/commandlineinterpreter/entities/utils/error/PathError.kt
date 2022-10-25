package com.architecturecourse.commandlineinterpreter.entities.utils.error

object PathError : Throwable("No such file or directory"), CLIError {
    override val code = 16
}