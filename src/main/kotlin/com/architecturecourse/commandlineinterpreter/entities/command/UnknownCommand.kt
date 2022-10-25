package com.architecturecourse.commandlineinterpreter.entities.command

import com.architecturecourse.commandlineinterpreter.entities.context.EnvironmentContext
import com.architecturecourse.commandlineinterpreter.entities.utils.error.ExternalCommandError
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

/* If something is entered that the interpreter does not know, call an external program */
class UnknownCommand(private val args: List<String>) : Command {
    override val expectedNumberOfArgs: Int? = null
    override fun execute(context: EnvironmentContext): Pair<Optional<String>, Int>{
        val isWindows = System.getProperty("os.name")
            .lowercase(Locale.getDefault()).startsWith("windows")

        val processBuilder = ProcessBuilder()
            .directory(context.getCurrentDirectory().toFile())

        if (isWindows) {
            processBuilder.command("cmd.exe /c ${args.joinToString(separator = " ")}")
        } else {
            processBuilder.command(java.lang.String.format("sh -c ${args.joinToString(separator = " ")}"))
        }

        val process: Process = processBuilder.start()

        val output = BufferedReader(InputStreamReader(process.inputStream)).lines().toList()
        val exitCode = process.waitFor()
        if(exitCode != 0) {
            val error = BufferedReader(InputStreamReader(process.errorStream)).lines().toList()
            throw ExternalCommandError(error.joinToString(separator = "\n"), exitCode)
        }
        return Optional.of(output.joinToString(separator = "\n")) to 0
    }
}