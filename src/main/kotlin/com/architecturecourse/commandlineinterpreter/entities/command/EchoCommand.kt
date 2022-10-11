package com.architecturecourse.commandlineinterpreter.entities.command

import com.architecturecourse.commandlineinterpreter.entities.context.VariableContext
import com.architecturecourse.commandlineinterpreter.entities.utils.error.WrongNumberOfArgumentsError
import java.util.*

/* echo - display the entered argument (or arguments) */
class EchoCommand(private val args: List<String>) : Command {
    override val expectedNumberOfArgs: Int? = null

    override fun execute(context: VariableContext): Pair<Optional<String>, Int> {
        return Optional.of(args.joinToString(separator = " ").trim()) to 0
    }
}
