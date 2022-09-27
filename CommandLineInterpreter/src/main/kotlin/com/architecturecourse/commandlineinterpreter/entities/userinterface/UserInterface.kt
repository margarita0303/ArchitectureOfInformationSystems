package com.architecturecourse.commandlineinterpreter.entities.userinterface

interface UserInterface {

    fun input(): String

    fun output(string: String)
}