package com.architecturecourse.commandlineinterpreter.entities.command

import com.architecturecourse.commandlineinterpreter.entities.context.EnvironmentContext
import com.architecturecourse.commandlineinterpreter.entities.utils.error.WrongNumberOfArgumentsError
import com.architecturecourse.commandlineinterpreter.entities.utils.exit.ExitInterruption
import java.util.*

/* exit - exit the interpreter */
class ExitCommand(args: List<String>) : Command {
    override val expectedNumberOfArgs: Int = 0
    init {
        if(args.size != expectedNumberOfArgs)
            throw WrongNumberOfArgumentsError
    }
    override fun execute(context: EnvironmentContext): Pair<Optional<String>, Int> {
        throw ExitInterruption
    }
}