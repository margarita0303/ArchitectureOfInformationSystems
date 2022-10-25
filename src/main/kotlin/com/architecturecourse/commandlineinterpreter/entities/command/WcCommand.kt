package com.architecturecourse.commandlineinterpreter.entities.command

import com.architecturecourse.commandlineinterpreter.entities.context.EnvironmentContext
import com.architecturecourse.commandlineinterpreter.entities.utils.error.FileError
import com.architecturecourse.commandlineinterpreter.entities.utils.error.WrongNumberOfArgumentsError
import java.io.BufferedReader
import java.io.FileReader
import java.util.*
import kotlin.io.path.div

/* wc [FILE] - print the number of lines, words and bytes in a file */
class WcCommand(private val args: List<String>) : Command {
    override val expectedNumberOfArgs: Int = 1
    init {
        if(args.size > expectedNumberOfArgs)
            throw WrongNumberOfArgumentsError
    }

    override fun execute(context: EnvironmentContext): Pair<Optional<String>, Int> {
        val path = context.getCurrentDirectory() / args[0]
        try {
            val reader = BufferedReader(FileReader(path.toFile(), Charsets.UTF_8))
            val lines = reader.lines().toList()
            return internalRunner(lines)
        } catch (e: Exception) {
            throw FileError
        }
    }

    override fun execute(context: EnvironmentContext, input: Optional<String>): Pair<Optional<String>, Int> {
        return if (args.size == 1 || input.isEmpty)
            execute(context)
        else
            internalRunner(listOf(input.get()))
    }

    private fun internalRunner(data: List<String>): Pair<Optional<String>, Int> {
        val linesCount = data.size
        val wordsCount = data.fold(0) { acc, line -> acc + line.split(" ").size }
        val bytes = data.sumOf { it.toByteArray().size }
        return Optional.of("lines: $linesCount, words: $wordsCount, bytes: $bytes") to 0
    }
}