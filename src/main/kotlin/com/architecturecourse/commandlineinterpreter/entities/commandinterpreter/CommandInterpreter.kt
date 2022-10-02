package com.architecturecourse.commandlineinterpreter.entities.commandinterpreter

import com.architecturecourse.commandlineinterpreter.entities.command.Command

/* Executes command */
interface CommandInterpreter {
    fun runCommand(command: Command, stream: String): Pair<String, Int>
    fun runCommandPipeline(commands: List<Command>): Pair<String, Int>
}