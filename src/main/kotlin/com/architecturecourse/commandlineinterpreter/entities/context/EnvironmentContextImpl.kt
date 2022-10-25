package com.architecturecourse.commandlineinterpreter.entities.context

import java.io.File
import java.nio.file.Path

class EnvironmentContextImpl(private val variableContext: VariableContext) : EnvironmentContext {
    private var currentDirectory: Path = File("").toPath().toRealPath()

    override fun getVariableContext(): VariableContext =
        variableContext

    override fun getCurrentDirectory(): Path =
        currentDirectory

    override fun setCurrentDirectory(path: Path) {
        currentDirectory = path.toRealPath()
    }
}