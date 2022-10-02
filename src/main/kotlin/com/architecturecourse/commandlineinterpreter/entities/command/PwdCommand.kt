package com.architecturecourse.commandlineinterpreter.entities.command

import com.architecturecourse.commandlineinterpreter.entities.context.VariableContext
import java.io.File

/* pwd - print current directory */
class PwdCommand : Command {
    override val expectedNumberOfArgs: Int = 0
    override fun execute(context: VariableContext): Pair<String, Int> {
        return File("").absolutePath to 0
    }
}