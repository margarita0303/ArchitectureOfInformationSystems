package com.architecturecourse.commandlineinterpreter.entities.commandfactory

import com.architecturecourse.commandlineinterpreter.entities.cmd.Cmd
import com.architecturecourse.commandlineinterpreter.entities.utils.Arg
import com.architecturecourse.commandlineinterpreter.entities.utils.CmdType

interface CommandFactory {
    fun createCommand(commandType: CmdType, args: List<Arg>): Cmd
}