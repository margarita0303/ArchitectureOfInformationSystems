package com.architecturecourse.commandlineinterpreter.entities.command

import com.architecturecourse.commandlineinterpreter.entities.context.EnvironmentContext
import com.architecturecourse.commandlineinterpreter.entities.utils.error.PathError
import com.architecturecourse.commandlineinterpreter.entities.utils.error.WrongNumberOfArgumentsError
import java.util.*
import kotlin.io.path.absolutePathString
import kotlin.io.path.notExists

/* cd [DIR] - change the working directory to absolute or relative directory DIR */
class CdCommand(private val args: List<String>) : Command {
    override val expectedNumberOfArgs: Int = 1

    init {
        if (args.size > expectedNumberOfArgs)
            throw WrongNumberOfArgumentsError
    }

    override fun execute(context: EnvironmentContext): Pair<Optional<String>, Int> {
        val currentDir = context.getCurrentDirectory()
        val homePath = context.getVariableContext().getVar("HOME") ?: currentDir.absolutePathString()
        val path = args.singleOrNull()
        val newPath = currentDir.resolve(path ?: homePath)

        if (newPath.notExists()) {
            throw PathError
        }

        context.setCurrentDirectory(newPath)
        return Optional.empty<String>() to 0
    }
}