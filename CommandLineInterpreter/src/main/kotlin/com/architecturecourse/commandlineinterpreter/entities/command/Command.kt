package com.architecturecourse.commandlineinterpreter.entities.command

import com.architecturecourse.commandlineinterpreter.entities.context.VariableContext

/* Interface for each command which can be executed by CLI */
interface Command {
    fun execute(context: VariableContext) : Pair<String, Int>

    val numberOfArgs: Int?
}