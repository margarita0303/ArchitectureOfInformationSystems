package com.architecturecourse.commandlineinterpreter.entities.command

import com.architecturecourse.commandlineinterpreter.entities.context.EnvironmentContext
import java.util.*

/* echo - display the entered argument (or arguments) */
class EchoCommand(private val args: List<String>) : Command {
    override val expectedNumberOfArgs: Int? = null

    override fun execute(context: EnvironmentContext): Pair<Optional<String>, Int> {
        return Optional.of(args.joinToString(separator = " ").trim()) to 0
    }
}
