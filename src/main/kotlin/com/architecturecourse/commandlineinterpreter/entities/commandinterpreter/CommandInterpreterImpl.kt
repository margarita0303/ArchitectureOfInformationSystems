package com.architecturecourse.commandlineinterpreter.entities.commandinterpreter

import com.architecturecourse.commandlineinterpreter.entities.command.Command
import com.architecturecourse.commandlineinterpreter.entities.context.VariableContext
import java.util.*

class CommandInterpreterImpl(private val variableContext: VariableContext) : CommandInterpreter {
    override fun runCommand(command: Command, stream: String): Pair<String, Int> {
        return command.execute(variableContext, Optional.of(stream))
    }
}