package com.architecturecourse.commandlineinterpreter.entities.commandfactory

import com.architecturecourse.commandlineinterpreter.entities.command.Command

interface CommandFactory {
    fun createCommand(commandType: String, args: Array<String>): Command
}