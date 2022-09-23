package com.architecturecourse.commandlineinterpreter.entities.commandfactory

import com.architecturecourse.commandlineinterpreter.entities.cmd.*
import com.architecturecourse.commandlineinterpreter.entities.utils.Arg
import com.architecturecourse.commandlineinterpreter.entities.utils.CmdType
import com.architecturecourse.commandlineinterpreter.entities.utils.error.WrongNumberOfArgumentsError

class CommandFactoryImpl : CommandFactory {
    override fun createCommand(commandType: CmdType, args: List<Arg>): Cmd {
        return when(commandType) {
            CmdType.Echo -> EchoCmd(args.map { it.s })
            CmdType.Cat -> {
                val cmd = CatCmd(args.map {it.s})
                if(!checkNumberOfArguments(cmd, args))
                    throw WrongNumberOfArgumentsError
                cmd
            }
            CmdType.Pwd ->  {
                val cmd = PwdCmd(args.map {it.s})
                if(!checkNumberOfArguments(cmd, args))
                    throw WrongNumberOfArgumentsError
                cmd
            }
            CmdType.Wc -> {
                val cmd = WcCmd(args.map {it.s})
                if(!checkNumberOfArguments(cmd, args))
                    throw WrongNumberOfArgumentsError
                cmd
            }
            CmdType.Exit -> {
                val cmd = ExitCmd(args.map {it.s})
                if(!checkNumberOfArguments(cmd, args))
                    throw WrongNumberOfArgumentsError
                cmd
            }
            CmdType.Unknown -> UnknownCmd(args.map {it.s})
            CmdType.Assign -> TODO("Do in next hw")
        }
    }

    private fun checkNumberOfArguments(cmd: Cmd, args: List<Arg>) : Boolean = args.size == cmd.numberOfArgs

}