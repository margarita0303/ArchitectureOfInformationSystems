package com.architecturecourse.commandlineinterpreter.entities.commandfactory

import com.architecturecourse.commandlineinterpreter.entities.command.Command
import com.architecturecourse.commandlineinterpreter.entities.utils.Arg
import com.architecturecourse.commandlineinterpreter.entities.utils.CommandData
import com.architecturecourse.commandlineinterpreter.entities.utils.CommandType

/* Creates Command instance by input CommandType */
interface CommandFactory {
    fun createCommand(commandType: CommandType, args: List<Arg>): Command

    fun createCommand(commandData: CommandData): Command
}