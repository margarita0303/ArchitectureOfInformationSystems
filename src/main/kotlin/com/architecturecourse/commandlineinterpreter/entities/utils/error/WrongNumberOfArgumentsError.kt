package com.architecturecourse.commandlineinterpreter.entities.utils.error

object WrongNumberOfArgumentsError : Throwable("Wrong number of arguments."), CLIError {
    override val code = 15
}