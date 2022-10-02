package com.architecturecourse.commandlineinterpreter.entities.command

import com.architecturecourse.commandlineinterpreter.entities.context.VariableContext
import java.util.*

/* Interface for each command which can be executed by CLI */
interface Command {
    fun execute(context: VariableContext): Pair<Optional<String>, Int>

    fun execute(context: VariableContext, input: Optional<String>): Pair<Optional<String>, Int> {
        return execute(context)
    }

    val expectedNumberOfArgs: Int?
}