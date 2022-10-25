package com.architecturecourse.commandlineinterpreter.entities.command

import com.architecturecourse.commandlineinterpreter.entities.context.EnvironmentContext
import com.architecturecourse.commandlineinterpreter.entities.utils.error.FileError
import com.architecturecourse.commandlineinterpreter.entities.utils.error.WrongNumberOfArgumentsError
import java.io.BufferedReader
import java.io.FileReader
import java.util.*
import kotlin.io.path.div

/* cat [FILE] - display the contents of the file */
class CatCommand(private val args: List<String>) : Command {
    override val expectedNumberOfArgs: Int = 1
    init {
        if(args.size > expectedNumberOfArgs)
            throw WrongNumberOfArgumentsError
    }

    override fun execute(context: EnvironmentContext): Pair<Optional<String>, Int> {
        val path = context.getCurrentDirectory() / args[0]
        return try {
            Optional.of(BufferedReader(FileReader(path.toFile(), Charsets.UTF_8)).lines().toList()
                .joinToString(separator = "\n").trim()) to 0
        } catch (e: Exception) {
            throw FileError
        }
    }

    override fun execute(context: EnvironmentContext, input: Optional<String>): Pair<Optional<String>, Int> {
        return if (args.size == 1 || input.isEmpty) {
            execute(context)
        } else {
            Optional.of(input.get()) to 0
        }
    }
}