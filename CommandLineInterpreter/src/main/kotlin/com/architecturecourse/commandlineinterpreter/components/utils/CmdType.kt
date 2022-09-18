package com.architecturecourse.commandlineinterpreter.components.utils

sealed interface CmdType {
    object Echo : CmdType {
        const val TAG = "echo"
    }
    object Cat : CmdType {
        const val TAG = "cat"
    }
    object Pwd : CmdType {
        const val TAG = "pwd"
    }
    object Wc : CmdType {
        const val TAG = "wc"
    }
    object Exit : CmdType {
        const val TAG = "exit"
    }
    object Unknown : CmdType
    object Assign : CmdType
}