package com.architecturecourse.commandlineinterpreter.components.utils

sealed interface CmdCategory {
    object Invoke : CmdCategory
    object Assign : CmdCategory
}