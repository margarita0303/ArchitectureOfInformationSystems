package com.architecturecourse.commandlineinterpreter.entities.context

import java.nio.file.Path

/* Storage current dir and variable context */
interface EnvironmentContext {

    fun getVariableContext(): VariableContext

    fun getCurrentDirectory(): Path

    fun setCurrentDirectory(path: Path)

}