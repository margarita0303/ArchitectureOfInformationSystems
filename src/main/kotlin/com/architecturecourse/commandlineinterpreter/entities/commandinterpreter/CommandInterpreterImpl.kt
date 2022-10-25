package com.architecturecourse.commandlineinterpreter.entities.commandinterpreter

import com.architecturecourse.commandlineinterpreter.entities.command.Command
import com.architecturecourse.commandlineinterpreter.entities.context.EnvironmentContext
import java.util.Optional

class CommandInterpreterImpl(private val environmentContext: EnvironmentContext) : CommandInterpreter {
    override fun runCommand(command: Command, stream: String): Pair<Optional<String>, Int> {
        return command.execute(environmentContext, Optional.of(stream))
    }

    override fun runCommandPipeline(commands: List<Command>): Pair<Optional<String>, Int> {
        var result = Optional.empty<String>()
        for (i in commands.indices) {
            val outData = commands[i].execute(environmentContext, result)
            result = outData.first
        }
        return result to 0
    }
}