package com.architecturecourse.commandlineinterpreter.entities.command

import com.architecturecourse.commandlineinterpreter.entities.context.EnvironmentContext
import com.architecturecourse.commandlineinterpreter.entities.utils.error.PathError
import com.architecturecourse.commandlineinterpreter.entities.utils.error.WrongNumberOfArgumentsError
import java.io.File
import java.util.Optional
import kotlin.io.path.isDirectory
import kotlin.io.path.listDirectoryEntries
import kotlin.io.path.name
import kotlin.io.path.notExists

class LsCommand(private val args: List<String>) : Command {

    override val expectedNumberOfArgs: Int = 1

    init {
        if (args.size > expectedNumberOfArgs) {
            throw WrongNumberOfArgumentsError
        }
    }

    override fun execute(context: EnvironmentContext): Pair<Optional<String>, Int> {
        val targetPath = context.getCurrentDirectory().resolve(
            args.singleOrNull() ?: "."
        )
        if (targetPath.notExists()) {
            throw PathError
        }
        val entries = if (targetPath.isDirectory()) {
            targetPath.listDirectoryEntries()
        } else {
            listOf(targetPath)
        }
        val output = entries.joinToString(System.lineSeparator()) {
            it.name + if (it.isDirectory()) File.separator else ""
        }
        return Optional.of(output) to 0
    }
}