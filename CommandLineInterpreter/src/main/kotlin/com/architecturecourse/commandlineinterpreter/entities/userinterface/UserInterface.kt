package com.architecturecourse.commandlineinterpreter.entities.userinterface

/* Read input from and show output to user */
interface UserInterface {
    fun input(): String

    fun output(string: String)
}