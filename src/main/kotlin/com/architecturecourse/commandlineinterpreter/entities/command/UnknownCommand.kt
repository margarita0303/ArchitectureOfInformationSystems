package com.architecturecourse.commandlineinterpreter.entities.command

import com.architecturecourse.commandlineinterpreter.entities.context.VariableContext
import com.architecturecourse.commandlineinterpreter.entities.singletons.SystemStateSingletonImpl
import com.architecturecourse.commandlineinterpreter.entities.utils.error.ExternalCommandError
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.util.*

/* If something is entered that the interpreter does not know, call an external program */
class UnknownCommand(private val args: List<String>) : Command {
    override val expectedNumberOfArgs: Int? = null
    override fun execute(context: VariableContext): Pair<Optional<String>, Int> {
        val isWindows = System.getProperty("os.name")
            .lowercase(Locale.getDefault()).startsWith("windows")

        val process: Process = if (isWindows) {
            Runtime.getRuntime()
                .exec("cmd.exe /c ${parseArguments(args).joinToString(separator = " ")}")
        } else {
            Runtime.getRuntime()
                .exec(java.lang.String.format("sh -c ${parseArguments(args).joinToString(separator = " ")}"))
        }

        val output = BufferedReader(InputStreamReader(process.inputStream)).lines().toList()
        val exitCode = process.waitFor()
        if (exitCode != 0) {
            val error = BufferedReader(InputStreamReader(process.errorStream)).lines().toList()
            throw ExternalCommandError(error.joinToString(separator = "\n"), exitCode)
        }
        return Optional.of(output.joinToString(separator = "\n")) to 0
    }

    private fun parseArguments(args: List<String>): List<String> {
        var res = ArrayList<String>()

        // Тут добавим какой-никакой пониматор ссылок ...
        // В общем случае решение скорее всего не верное
        // Будем считать что аргументы начинающиеся на - это ключи, а не путь
        // Если он начинается на / то скорее всего это уже абсолютный путь
        // Для остальных аргументов, если существует файл по полному пути из текущей дериктории
        // Делаем замену для этого аргумента

        args.forEach {
            if (!it.startsWith("-") && !it.startsWith("/")) {
                var newArg = SystemStateSingletonImpl.instance.getFullPath(it)
                if(File(newArg).exists()){
                    res.add(newArg)
                } else {
                    res.add(it)
                }
            } else {
                res.add(it)
            }
        }

        return res
    }
}