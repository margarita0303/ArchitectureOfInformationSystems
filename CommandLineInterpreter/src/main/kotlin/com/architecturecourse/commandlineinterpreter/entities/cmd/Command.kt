package com.architecturecourse.commandlineinterpreter.entities.cmd

import com.architecturecourse.commandlineinterpreter.entities.context.VariableContext

interface Command {
    fun execute(context: VariableContext) : Pair<String, Int>

    val numberOfArgs: Int?
}