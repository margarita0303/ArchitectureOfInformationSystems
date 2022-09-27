package com.architecturecourse.commandlineinterpreter.entities.commandfactory

import com.architecturecourse.commandlineinterpreter.entities.command.*
import com.architecturecourse.commandlineinterpreter.entities.utils.Arg
import com.architecturecourse.commandlineinterpreter.entities.utils.CommandData
import com.architecturecourse.commandlineinterpreter.entities.utils.CommandType
import com.architecturecourse.commandlineinterpreter.entities.utils.error.WrongNumberOfArgumentsError

class CommandFactoryImpl : CommandFactory {

    override fun createCommand(commandData: CommandData): Command {
        val commandType = commandData.type
        val args = commandData.args
        return when (commandType) {
            CommandType.Echo -> EchoCommand(args.map { it.data })
            CommandType.Cat -> {
                val cmd = CatCommand(args.map { it.data })
                if (!checkNumberOfArguments(cmd, args))
                    throw WrongNumberOfArgumentsError
                cmd
            }

            CommandType.Pwd -> {
                val cmd = PwdCommand(args.map { it.data })
                if (!checkNumberOfArguments(cmd, args))
                    throw WrongNumberOfArgumentsError
                cmd
            }

            CommandType.Wc -> {
                val cmd = WcCommand(args.map { it.data })
                if (!checkNumberOfArguments(cmd, args))
                    throw WrongNumberOfArgumentsError
                cmd
            }

            CommandType.Exit -> {
                val cmd = ExitCommand(args.map { it.data })
                if (!checkNumberOfArguments(cmd, args))
                    throw WrongNumberOfArgumentsError
                cmd
            }

            CommandType.Unknown -> UnknownCommand(args.map { it.data })
            CommandType.Assign -> TODO("Do in next hw")
        }
    }

    override fun createCommand(commandType: CommandType, args: List<Arg>): Command {
        return createCommand(CommandData(commandType, args))
    }

    private fun checkNumberOfArguments(command: Command, args: List<Arg>): Boolean = args.size == command.numberOfArgs

}