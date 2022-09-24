package com.architecturecourse.commandlineinterpreter.configurations

import com.architecturecourse.commandlineinterpreter.entities.commandfactory.CommandFactory
import com.architecturecourse.commandlineinterpreter.entities.commandinterpreter.CommandInterpreter
import com.architecturecourse.commandlineinterpreter.entities.interpretersession.InterpreterSession
import com.architecturecourse.commandlineinterpreter.entities.interpretersession.InterpreterSessionImpl
import com.architecturecourse.commandlineinterpreter.entities.parser.Parser
import com.architecturecourse.commandlineinterpreter.entities.tokenizer.Tokenizer
import com.architecturecourse.commandlineinterpreter.entities.userinterface.UserInterface
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class InterpreterSessionConfiguration {

    @Bean
    fun interpreterSession(
        userInterface: UserInterface,
        commandFactory: CommandFactory,
        commandInterpreter: CommandInterpreter,
        tokenizer: Tokenizer,
        parser: Parser
    ): InterpreterSession {
        return InterpreterSessionImpl(
            userInterface, commandFactory, commandInterpreter, tokenizer, parser
        )
    }

}