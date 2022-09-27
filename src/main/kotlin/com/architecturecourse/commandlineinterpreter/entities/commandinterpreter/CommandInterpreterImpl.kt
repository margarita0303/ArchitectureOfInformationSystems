package com.architecturecourse.commandlineinterpreter.entities.commandinterpreter

import com.architecturecourse.commandlineinterpreter.entities.command.Command
import com.architecturecourse.commandlineinterpreter.entities.context.VariableContext

class CommandInterpreterImpl(private val variableContext: VariableContext) : CommandInterpreter {
    override fun runCommand(command: Command): Pair<String, Int> {
        return command.execute(variableContext)
    }
}