package com.architecturecourse.commandlineinterpreter.entities.command

import com.architecturecourse.commandlineinterpreter.entities.context.EnvironmentContext
import java.util.Optional

/* Interface for each command which can be executed by CLI */
interface Command {

    fun execute(context: EnvironmentContext): Pair<Optional<String>, Int>

    fun execute(context: EnvironmentContext, input: Optional<String>): Pair<Optional<String>, Int> {
        return execute(context)
    }

    val expectedNumberOfArgs: Int?
}