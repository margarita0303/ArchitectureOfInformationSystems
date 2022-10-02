package com.architecturecourse.commandlineinterpreter.entities.command

import com.architecturecourse.commandlineinterpreter.entities.context.VariableContext
import com.architecturecourse.commandlineinterpreter.entities.utils.exit.ExitInterruption

/* exit - exit the interpreter */
class ExitCommand : Command {
    override val expectedNumberOfArgs: Int = 0
    override fun execute(context: VariableContext): Pair<String, Int> {
        throw ExitInterruption
    }
}