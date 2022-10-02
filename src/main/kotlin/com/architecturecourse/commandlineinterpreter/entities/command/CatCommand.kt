package com.architecturecourse.commandlineinterpreter.entities.command

import com.architecturecourse.commandlineinterpreter.entities.context.VariableContext
import com.architecturecourse.commandlineinterpreter.entities.utils.error.FileError
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.util.*

/* cat [FILE] - display the contents of the file */
class CatCommand(private val args: List<String>) : Command {
    override val expectedNumberOfArgs: Int? = null

    override fun execute(context: VariableContext): Pair<String, Int> {
        val path = args[0]
        return try {
            BufferedReader(FileReader(File(path), Charsets.UTF_8)).lines().toList()
                .joinToString(separator = "\n").trim() to 0
        } catch (e: Exception) {
            throw FileError
        }
    }

    override fun execute(context: VariableContext, input: Optional<String>): Pair<String, Int> {
        return if (input.isEmpty) {
            execute(context)
        } else {
            input.get() to 0
        }
    }
}