package com.architecturecourse.commandlineinterpreter.entities.command

import com.architecturecourse.commandlineinterpreter.entities.context.VariableContext
import com.architecturecourse.commandlineinterpreter.entities.utils.error.FileError
import java.io.BufferedReader
import java.io.File
import java.io.FileReader

/* wc [FILE] - print the number of lines, words and bytes in a file */
class WcCommand(private val args: List<String>) : Command {
    override val numberOfArgs: Int = 1
    override fun execute(context: VariableContext): Pair<String, Int> {
        val path = args[0]
        val file = File(path)
        try {
            val reader = BufferedReader(FileReader(File(path), Charsets.UTF_8))
            val lines = reader.lines().toList()
            val linesCount = lines.size
            val wordsCount = lines.fold(0) {acc, line ->  acc + line.split(" ").size}
            val bytes = file.length()
            return "lines: $linesCount, words: $wordsCount, bytes: $bytes" to 0
        } catch (e: Exception) {
            throw FileError
        }
    }
}