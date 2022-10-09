package com.architecturecourse.commandlineinterpreter.entities.command

import com.architecturecourse.commandlineinterpreter.entities.command.utils.GrepArgsConfiguration
import com.architecturecourse.commandlineinterpreter.entities.context.VariableContext
import com.architecturecourse.commandlineinterpreter.entities.utils.error.FileError
import com.architecturecourse.commandlineinterpreter.entities.utils.error.WrongNumberOfArgumentsError
import picocli.CommandLine
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.util.*

class GrepCommand(private val args: List<String>) : Command {
    override fun execute(context: VariableContext): Pair<Optional<String>, Int> = execute(context, Optional.empty())


    override fun execute(context: VariableContext, input: Optional<String>): Pair<Optional<String>, Int> {
        val argParser = GrepArgsConfiguration()
        CommandLine(argParser).parseArgs(*(args.toTypedArray()))

        val linesAfter = argParser.lines ?: 0
        val caseInsensitive = argParser.caseInsensitive
        val fullWord = argParser.fullWord
        val regex = if (caseInsensitive) argParser.word.toRegex(RegexOption.IGNORE_CASE) else argParser.word.toRegex()

        try {
            val lines = argParser.file?.let { path ->
                BufferedReader(FileReader(File(path), Charsets.UTF_8)).lines().toList()
            } ?: if (input.isPresent) input.get().lines() else throw WrongNumberOfArgumentsError

            if (fullWord) {
                val result = lines.map { line ->
                    line.indexesOfAll(argParser.word, caseInsensitive).filter { index ->
                        checkBorders(line, index, argParser.word)
                    } to line
                }.fold(listOf<String>() to 0) { (accList, accCnt), (matches, line) ->
                    foldLinesRoutine(accList, accCnt, matches, line, linesAfter)
                }.first
                return Optional.of(result.joinToString(separator = "\n")) to 0
            } else {
                val result = lines.map { line ->
                    regex.findAll(line).toList() to line
                }.fold(listOf<String>() to 0) { (accList, accCnt), (matches, line) ->
                    foldLinesRoutine(accList, accCnt, matches, line, linesAfter)
                }.first
                return Optional.of(result.joinToString(separator = "\n")) to 0
            }
        } catch (e: WrongNumberOfArgumentsError) {
            throw e
        } catch (e: Throwable) {
            throw FileError
        }
    }

    override val expectedNumberOfArgs: Int? = null

    private fun foldLinesRoutine(
        accList: List<String>,
        accCnt: Int,
        matches: List<Any>,
        line: String,
        linesAfter: Int
    ): Pair<List<String>, Int> =
        if (matches.isEmpty()) {
            if (accCnt > 0) accList + line to accCnt - 1 else accList to accCnt
        } else {
            accList + line to linesAfter
        }

    private fun checkBorders(line: String, index: Int, word: String): Boolean =
        (index == 0 || line[index - 1].isNWCC()) &&
                (index + word.length >= line.length || line[index + word.length].isNWCC())


}

fun String.indexesOfAll(s: String, ignoreCase: Boolean = false, pos: Int = 0): List<Int> {
    return when (val index = this.indexOf(s, pos, ignoreCase = ignoreCase)) {
        -1 -> emptyList()
        else -> listOf(index) + this.indexesOfAll(s, ignoreCase, index + 1)
    }
}

fun Char.isNWCC(): Boolean = !this.isLetterOrDigit() && this != '_'