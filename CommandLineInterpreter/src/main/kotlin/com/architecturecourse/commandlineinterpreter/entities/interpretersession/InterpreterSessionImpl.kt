package com.architecturecourse.commandlineinterpreter.entities.interpretersession

import com.architecturecourse.commandlineinterpreter.entities.commandfactory.CommandFactory
import com.architecturecourse.commandlineinterpreter.entities.commandinterpreter.CommandInterpreter
import com.architecturecourse.commandlineinterpreter.entities.context.VariableContext
import com.architecturecourse.commandlineinterpreter.entities.lexer.Lexer
import com.architecturecourse.commandlineinterpreter.entities.substitutor.Substitutor
import com.architecturecourse.commandlineinterpreter.entities.userinterface.UserInterface

class InterpreterSessionImpl(
    private val userInterface: UserInterface,
    private val lexer: Lexer,
    private val substitutor: Substitutor,
    private val commandFactory: CommandFactory,
    private val commandInterpreter: CommandInterpreter,
    private val variableContext: VariableContext
) : InterpreterSession {
    override fun launch() {
        println("KINDA RUN ${userInterface.getUserName()}")
    }
}