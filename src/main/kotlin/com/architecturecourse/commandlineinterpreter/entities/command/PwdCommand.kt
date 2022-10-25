package com.architecturecourse.commandlineinterpreter.entities.command

import com.architecturecourse.commandlineinterpreter.entities.context.EnvironmentContext
import com.architecturecourse.commandlineinterpreter.entities.utils.error.WrongNumberOfArgumentsError
import java.util.Optional
import kotlin.io.path.absolutePathString

/* pwd - print current directory */
class PwdCommand(args: List<String>) : Command {
    override val expectedNumberOfArgs: Int = 0
    init {
        if(args.size != expectedNumberOfArgs)
            throw WrongNumberOfArgumentsError
    }
    override fun execute(context: EnvironmentContext): Pair<Optional<String>, Int> {
        return Optional.of(context.getCurrentDirectory().absolutePathString()) to 0
    }
}