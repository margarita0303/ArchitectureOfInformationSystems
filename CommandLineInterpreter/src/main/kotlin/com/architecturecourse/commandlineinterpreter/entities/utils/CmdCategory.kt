package com.architecturecourse.commandlineinterpreter.entities.utils

sealed interface CmdCategory {
    object Invoke : CmdCategory
    object Assign : CmdCategory
}