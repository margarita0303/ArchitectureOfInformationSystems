package com.architecturecourse.commandlineinterpreter.entities.interpretersession

import com.architecturecourse.commandlineinterpreter.entities.commandfactory.CommandFactory
import com.architecturecourse.commandlineinterpreter.entities.commandinterpreter.CommandInterpreter
import com.architecturecourse.commandlineinterpreter.entities.parser.Parser
import com.architecturecourse.commandlineinterpreter.entities.singletons.SystemStateSingletonImpl
import com.architecturecourse.commandlineinterpreter.entities.tokenizer.Tokenizer
import com.architecturecourse.commandlineinterpreter.entities.userinterface.UserInterface
import com.architecturecourse.commandlineinterpreter.entities.utils.error.CLIError
import com.architecturecourse.commandlineinterpreter.entities.utils.exit.ExitInterruption

class InterpreterSessionImpl(
    private val userInterface: UserInterface,
    private val commandFactory: CommandFactory,
    private val commandInterpreter: CommandInterpreter,
    private val tokenizer: Tokenizer,
    private val parser: Parser,
) : InterpreterSession {
    override fun launch() {

        println(SystemStateSingletonImpl.instance.getPath())

        while (true) {
            try {
                val data = userInterface.input()
                val tokens = tokenizer.tokenize(data)
                val commandDataList = parser.parse(tokens)
                val commands = commandDataList.map {
                    commandFactory.createCommand(it)
                }
                val result = commandInterpreter.runCommandPipeline(commands)
                if(!result.first.isEmpty)
                    userInterface.output(result.first.get())
            } catch (e: Throwable) {
                when (e) {
                    ExitInterruption -> return
                    is CLIError -> {
                        userInterface.output("Something went wrong: ${e.message}")
                        userInterface.output("Process finished with exit code ${e.code}.")
                    }

                    else -> {
                        userInterface.output("Something went wrong: ${e.message})")
                        userInterface.output("Process finished with exit code $NOT_CLI_ERROR_CODE.")
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