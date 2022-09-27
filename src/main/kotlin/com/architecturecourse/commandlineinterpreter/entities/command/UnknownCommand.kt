package com.architecturecourse.commandlineinterpreter.entities.command

import com.architecturecourse.commandlineinterpreter.entities.context.VariableContext
import com.architecturecourse.commandlineinterpreter.entities.utils.error.ExternalCommandError
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

/* If something is entered that the interpreter does not know, call an external program */
class UnknownCommand(private val args: List<String>) : Command {
    override val numberOfArgs: Int? = null
    override fun execute(context: VariableContext): Pair<String, Int> {
        val isWindows = System.getProperty("os.name")
            .lowercase(Locale.getDefault()).startsWith("windows")

        val process: Process = if (isWindows) {
            Runtime.getRuntime()
                .exec("cmd.exe /c ${args.joinToString(separator = " ")}")
        } else {
            Runtime.getRuntime()
                .exec(java.lang.String.format("sh -c ${args.joinToString(separator = " ")}"))
        }

        val output = BufferedReader(InputStreamReader(process.inputStream)).lines().toList()
        val exitCode = process.waitFor()
        if(exitCode != 0) {
            val error = BufferedReader(InputStreamReader(process.errorStream)).lines().toList()
            throw ExternalCommandError(error.joinToString(separator = "\n"), exitCode)
        }
        return output.joinToString(separator = "\n") to 0
    }
}