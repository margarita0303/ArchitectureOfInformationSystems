package com.architecturecourse.commandlineinterpreter.entities.utils.error

object EmptyTokenListError : Throwable("Empty tokens list."), CLIError {
    override val code = 11
}