package com.architecturecourse.commandlineinterpreter.entities.commandinterpreter

import com.architecturecourse.commandlineinterpreter.entities.command.Command

/* Executes command */
interface CommandInterpreter {
    fun runCommand(command: Command): Pair<String, Int>
}