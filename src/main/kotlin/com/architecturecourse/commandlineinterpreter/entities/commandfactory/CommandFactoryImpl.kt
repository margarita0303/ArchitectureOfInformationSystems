package com.architecturecourse.commandlineinterpreter.entities.commandfactory

import com.architecturecourse.commandlineinterpreter.entities.command.*
import com.architecturecourse.commandlineinterpreter.entities.utils.Arg
import com.architecturecourse.commandlineinterpreter.entities.utils.CommandData
import com.architecturecourse.commandlineinterpreter.entities.utils.CommandType

class CommandFactoryImpl : CommandFactory {

    override fun createCommand(commandData: CommandData): Command {
        val commandType = commandData.type
        val argStrings = commandData.args.map { it.data }
        return when (commandType) {
            CommandType.Echo -> EchoCommand(argStrings)
            CommandType.Cat -> CatCommand(argStrings)
            CommandType.Pwd -> PwdCommand(argStrings)
            CommandType.Wc -> WcCommand(argStrings)
            CommandType.Exit -> ExitCommand(argStrings)
            CommandType.Grep -> GrepCommand(argStrings)
            CommandType.Unknown -> UnknownCommand(argStrings)
            CommandType.Assign -> AssignCommand(argStrings)
            CommandType.Ls -> LsCommand(argStrings)
            CommandType.Cd -> CdCommand(argStrings)
        }
    }

    override fun createCommand(commandType: CommandType, args: List<Arg>): Command {
        return createCommand(CommandData(commandType, args))
    }

}