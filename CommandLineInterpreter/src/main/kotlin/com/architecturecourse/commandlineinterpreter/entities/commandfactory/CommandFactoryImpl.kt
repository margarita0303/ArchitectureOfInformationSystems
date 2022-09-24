package com.architecturecourse.commandlineinterpreter.entities.commandfactory

import com.architecturecourse.commandlineinterpreter.entities.cmd.*
import com.architecturecourse.commandlineinterpreter.entities.utils.Arg
import com.architecturecourse.commandlineinterpreter.entities.utils.CmdType
import com.architecturecourse.commandlineinterpreter.entities.utils.error.WrongNumberOfArgumentsError

class CommandFactoryImpl : CommandFactory {
    override fun createCommand(commandType: CmdType, args: List<Arg>): Command {
        return when(commandType) {
            CmdType.Echo -> EchoCommand(args.map { it.s })
            CmdType.Cat -> {
                val cmd = CatCommand(args.map {it.s})
                if(!checkNumberOfArguments(cmd, args))
                    throw WrongNumberOfArgumentsError
                cmd
            }
            CmdType.Pwd ->  {
                val cmd = PwdCommand(args.map {it.s})
                if(!checkNumberOfArguments(cmd, args))
                    throw WrongNumberOfArgumentsError
                cmd
            }
            CmdType.Wc -> {
                val cmd = WcCommand(args.map {it.s})
                if(!checkNumberOfArguments(cmd, args))
                    throw WrongNumberOfArgumentsError
                cmd
            }
            CmdType.Exit -> {
                val cmd = ExitCommand(args.map {it.s})
                if(!checkNumberOfArguments(cmd, args))
                    throw WrongNumberOfArgumentsError
                cmd
            }
            CmdType.Unknown -> UnknownCommand(args.map {it.s})
            CmdType.Assign -> TODO("Do in next hw")
        }
    }

    private fun checkNumberOfArguments(command: Command, args: List<Arg>) : Boolean = args.size == command.numberOfArgs

}