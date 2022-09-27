package com.architecturecourse.commandlineinterpreter.entities.interpretersession

import com.architecturecourse.commandlineinterpreter.entities.commandfactory.CommandFactory
import com.architecturecourse.commandlineinterpreter.entities.commandinterpreter.CommandInterpreter
import com.architecturecourse.commandlineinterpreter.entities.parser.Parser
import com.architecturecourse.commandlineinterpreter.entities.tokenizer.Tokenizer
import com.architecturecourse.commandlineinterpreter.entities.userinterface.UserInterface
import com.architecturecourse.commandlineinterpreter.entities.utils.error.CLIError
import com.architecturecourse.commandlineinterpreter.entities.utils.exit.ExitInterruption

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
                val (result, _) = commandInterpreter.runCommand(command)
                userInterface.output(result)
            } catch (e: Throwable) {
                when (e) {
                    ExitInterruption -> return
                    is CLIError -> {
                        println("Something went wrong: ${e.message}")
                        println("Process finished with exit code ${e.code}.")
                    }
                    else -> {
                        println("Something went wrong: ${e.message})")
                        println("Process finished with exit code $NOT_CLI_ERROR_CODE.")
                    }
                }
            }
            // TODO: We need to add better output and maybe pass "onOutput" callback
            //       to executor to allow to write during execution
        }
    }

    companion object {
        private const val NOT_CLI_ERROR_CODE = 128
    }
}