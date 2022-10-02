package com.architecturecourse.commandlineinterpreter.entities.command

import com.architecturecourse.commandlineinterpreter.entities.context.VariableContext
import java.util.*

/* Interface for each command which can be executed by CLI */
interface Command {
    fun execute(context: VariableContext): Pair<String, Int>

    fun execute(context: VariableContext, input: Optional<String>): Pair<String, Int> {
        return execute(context)
    }

    val numberOfArgs: Int?
}