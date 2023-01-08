package com.architecturecourse.commandlineinterpreter.entities.command

import com.architecturecourse.commandlineinterpreter.entities.context.VariableContext
import com.architecturecourse.commandlineinterpreter.entities.singletons.SystemStateSingletonImpl
import com.architecturecourse.commandlineinterpreter.entities.utils.error.WrongNumberOfArgumentsError
import java.io.File
import java.util.*

/* pwd - print current directory */
class PwdCommand(args: List<String>) : Command {
    override val expectedNumberOfArgs: Int = 0
    init {
        if(args.size != expectedNumberOfArgs)
            throw WrongNumberOfArgumentsError
    }
    override fun execute(context: VariableContext): Pair<Optional<String>, Int> {
        return Optional.of(SystemStateSingletonImpl.instance.getPath()) to 0
    }
}