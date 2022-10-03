package com.architecturecourse.commandlineinterpreter.entities.commandinterpreter

import com.architecturecourse.commandlineinterpreter.entities.command.Command
import java.util.Optional

/* Executes command */
interface CommandInterpreter {
    fun runCommand(command: Command, stream: String): Pair<Optional<String>, Int>
    fun runCommandPipeline(commands: List<Command>): Pair<Optional<String>, Int>
}