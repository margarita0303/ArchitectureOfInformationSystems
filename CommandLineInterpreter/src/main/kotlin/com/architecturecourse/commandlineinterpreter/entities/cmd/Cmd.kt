package com.architecturecourse.commandlineinterpreter.entities.cmd

import com.architecturecourse.commandlineinterpreter.entities.context.VariableContext

interface Cmd {
    fun execute(context: VariableContext) : Pair<String, Int>

    val args: List<String>
    val numberOfArgs: Int?
}