package com.architecturecourse.commandlineinterpreter.entities.commandinterpreter

import com.architecturecourse.commandlineinterpreter.entities.command.Command
import com.architecturecourse.commandlineinterpreter.entities.context.VariableContext

class CommandInterpreterImpl(private val variableContext: VariableContext) : CommandInterpreter {
    override fun runCommand(command: Command): String {
        // TODO: Probably we will add interpreter dependency here
        return command.execute(variableContext).toString()
    }
}