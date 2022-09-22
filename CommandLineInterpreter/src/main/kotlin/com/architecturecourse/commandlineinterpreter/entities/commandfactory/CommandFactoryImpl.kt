package com.architecturecourse.commandlineinterpreter.entities.commandfactory

import com.architecturecourse.commandlineinterpreter.entities.command.Command
import com.architecturecourse.commandlineinterpreter.entities.utils.CmdType
import com.architecturecourse.commandlineinterpreter.entities.utils.error.NotEnoughArgumentsError

class CommandFactoryImpl : CommandFactory {
    override fun createCommand(commandType: String, args: Array<String>): Command {
        if ((commandType == CmdType.Cat.TAG) or (commandType == CmdType.Wc.TAG)) {
            if (args.isEmpty()) throw NotEnoughArgumentsError
        }
        return Command(commandType, args)
    }
}