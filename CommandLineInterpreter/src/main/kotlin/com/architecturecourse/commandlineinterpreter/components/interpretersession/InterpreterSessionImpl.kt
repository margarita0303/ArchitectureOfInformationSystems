package com.architecturecourse.commandlineinterpreter.components.interpretersession

import com.architecturecourse.commandlineinterpreter.components.userinterface.UserInterface

class InterpreterSessionImpl(private val userInterface: UserInterface) : InterpreterSession {
    override fun launch() {
        println("KINDA RUN ${userInterface.getUserName()}")
    }
}