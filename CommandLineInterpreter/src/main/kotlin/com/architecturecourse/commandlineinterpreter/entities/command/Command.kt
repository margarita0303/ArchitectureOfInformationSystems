package com.architecturecourse.commandlineinterpreter.entities.command

import com.architecturecourse.commandlineinterpreter.entities.utils.CmdType
import com.architecturecourse.commandlineinterpreter.entities.utils.error.ExitError
import com.architecturecourse.commandlineinterpreter.entities.utils.error.FileError
import java.io.BufferedReader
import java.io.File
import java.io.FileReader


class Command(private val commandType: String, private val args: Array<String>) {

    /* Execute command */
    fun execute(variables: MutableMap<String, String>): String {
        return when (commandType) {
            CmdType.Echo.TAG -> executeEcho(variables)
            CmdType.Cat.TAG -> executeCat(variables)
            CmdType.Pwd.TAG -> executePwd(variables)
            CmdType.Wc.TAG -> executeWc(variables)
            CmdType.Exit.TAG -> executeExit(variables)
            CmdType.Assign.TAG -> executeAssign(variables)
            else -> executeUnknown(variables)
        }
    }

    /* echo - display the entered argument (or arguments) */
    private fun executeEcho(variables: MutableMap<String, String>): String {
        var output = ""
        for (element in args) {
            output += "$element "
        }
        return output.trim()
    }

    /* cat [FILE] - display the contents of the file */
    private fun executeCat(variables: MutableMap<String, String>): String {
        val path = args[0]
        var data = ""
        try {
            val reader = BufferedReader(FileReader(File(path), Charsets.UTF_8))
            reader.lines().forEach { data += (it + "\n") }
            return data.trim()
        }
        catch (e: Exception) {
            throw FileError
        }
    }

    /* pwd - print current directory */
    private fun executePwd(variables: MutableMap<String, String>): String {
        return File("").absolutePath
    }

    /* wc [FILE] - print the number of lines, words and bytes in a file */
    private fun executeWc(variables: MutableMap<String, String>): String {
        val path = args[0]
        val file = File(path)
        try {
            val reader = BufferedReader(FileReader(File(path), Charsets.UTF_8))
            var lines = 0
            var words = 0
            val bytes = file.length()
            for (line in reader.lines()) {
                lines++
                words += line.split(" ").size
            }
            return "lines: $lines, words: $words, bytes: $bytes"
        }
        catch (e: Exception) {
            throw FileError
        }
    }

    /* exit - exit the interpreter */
    private fun executeExit(variables: MutableMap<String, String>): String {
        throw ExitError
    }

    /* If something is entered that the interpreter does not know, call an external program */
    private fun executeAssign(variables: MutableMap<String, String>): String {
        TODO("Do in next hw")
    }

    /* If something is entered that the interpreter does not know, call an external program */
    private fun executeUnknown(variables: MutableMap<String, String>): String {
        TODO("Need to discuss")
    }
}