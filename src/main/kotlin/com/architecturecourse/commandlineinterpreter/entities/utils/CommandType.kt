package com.architecturecourse.commandlineinterpreter.entities.utils

sealed interface CommandType {
    object Echo : CommandType {
        const val TAG = "echo"
    }
    object Cat : CommandType {
        const val TAG = "cat"
    }
    object Pwd : CommandType {
        const val TAG = "pwd"
    }
    object Wc : CommandType {
        const val TAG = "wc"
    }
    object Exit : CommandType {
        const val TAG = "exit"
    }
    object Assign : CommandType {
        const val TAG = "="
    }
    object Grep : CommandType {
        const val TAG = "grep"
    }
    object Cd : CommandType {
        const val TAG = "cd"
    }

    object Ls : CommandType {
        const val TAG = "ls"
    }

    object Unknown : CommandType
}