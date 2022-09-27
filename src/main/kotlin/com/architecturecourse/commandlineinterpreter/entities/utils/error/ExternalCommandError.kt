package com.architecturecourse.commandlineinterpreter.entities.utils.error

class ExternalCommandError(override val message: String?, override val code: Int): Throwable(), CLIError