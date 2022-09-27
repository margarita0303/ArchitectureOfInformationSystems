package com.architecturecourse.commandlineinterpreter.entities.interpretersession

import com.architecturecourse.commandlineinterpreter.entities.commandfactory.CommandFactory
import com.architecturecourse.commandlineinterpreter.entities.commandinterpreter.CommandInterpreter
import com.architecturecourse.commandlineinterpreter.entities.parser.Parser
import com.architecturecourse.commandlineinterpreter.entities.tokenizer.Tokenizer
import com.architecturecourse.commandlineinterpreter.entities.userinterface.UserInterface

class InterpreterSessionImpl(
    private val userInterface: UserInterface,
    private val commandFactory: CommandFactory,
    private val commandInterpreter: CommandInterpreter,
    private val tokenizer: Tokenizer,
    private val parser: Parser
) : InterpreterSession {
    override fun launch() {
        while (true) {
            try {
                val data = userInterface.input()
                val tokens = tokenizer.tokenize(data)
                val commandData = parser.parse(tokens)
                val command = commandFactory.createCommand(commandData)
                val result = commandInterpreter.runCommand(command)
                userInterface.output(result.toString())
            } catch (e: Exception) {
                println("Something went wrong ${e.message}")
            }
            // TODO: We need to add better output and maybe pass "onOutput" callback
            //       to executor to allow to write during execution
        }
    }
}