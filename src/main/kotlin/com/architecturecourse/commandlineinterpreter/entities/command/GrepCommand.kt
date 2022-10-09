package com.architecturecourse.commandlineinterpreter.entities.command

import com.architecturecourse.commandlineinterpreter.entities.context.VariableContext
import com.architecturecourse.commandlineinterpreter.entities.utils.Arg
import com.architecturecourse.commandlineinterpreter.entities.utils.error.FileError
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.util.*

class GrepCommand(private val args: List<Arg>) : Command {
    override fun execute(context: VariableContext): Pair<Optional<String>, Int> {
        val A = 0
        val I = false
        val W = true
        val regex = if (I) args[0].data.toRegex(RegexOption.IGNORE_CASE) else args[0].data.toRegex()
        val path = args[1].data

        try {
            val lines = BufferedReader(FileReader(File(path), Charsets.UTF_8)).lines().toList()
            if (W) {
                val result = lines.map { line ->
                    line.indexesOfAll(args[0].data).filter { index ->
                        checkBorders(line, index)
                    } to line
                }.fold(listOf<String>() to 0) { (accList, accCnt), (matches, line) ->
                    foldLinesRoutine(accList, accCnt, matches, line, A)
                }.first
                return Optional.of(result.joinToString(separator = "\n")) to 0
            } else {
                val result = lines.map { line ->
                    regex.findAll(line).toList() to line
                }.fold(listOf<String>() to 0) { (accList, accCnt), (matches, line) ->
                    foldLinesRoutine(accList, accCnt, matches, line, A)
                }.first
                return Optional.of(result.joinToString(separator = "\n")) to 0
            }
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
        A: Int
    ): Pair<List<String>, Int> =
        if (matches.isEmpty()) {
            if (accCnt > 0) accList + line to accCnt - 1 else accList to accCnt
        } else {
            accList + line to A
        }

    private fun checkBorders(line: String, index: Int): Boolean = (index == 0 || line[index - 1].isNWCC()) &&
            (index + args[0].data.length >= line.length || line[index + args[0].data.length].isNWCC())


}

fun String.indexesOfAll(s: String, pos: Int = 0): List<Int> {
    return when (val index = this.indexOf(s, pos)) {
        -1 -> emptyList()
        else -> listOf(index) + this.indexesOfAll(s, index + 1)
    }
}

fun Char.isNWCC(): Boolean = !this.isLetterOrDigit() && this != '_'