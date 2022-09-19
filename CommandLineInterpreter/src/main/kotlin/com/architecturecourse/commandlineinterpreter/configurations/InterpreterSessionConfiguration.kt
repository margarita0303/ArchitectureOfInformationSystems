package com.architecturecourse.commandlineinterpreter.configurations

import com.architecturecourse.commandlineinterpreter.entities.commandfactory.CommandFactory
import com.architecturecourse.commandlineinterpreter.entities.commandinterpreter.CommandInterpreter
import com.architecturecourse.commandlineinterpreter.entities.context.VariableContext
import com.architecturecourse.commandlineinterpreter.entities.interpretersession.InterpreterSession
import com.architecturecourse.commandlineinterpreter.entities.interpretersession.InterpreterSessionImpl
import com.architecturecourse.commandlineinterpreter.entities.lexer.Lexer
import com.architecturecourse.commandlineinterpreter.entities.substitutor.Substitutor
import com.architecturecourse.commandlineinterpreter.entities.userinterface.UserInterface
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class InterpreterSessionConfiguration {

    @Bean
    fun interpreterSession(
        userInterface: UserInterface,
        lexer: Lexer,
        substitutor: Substitutor,
        commandFactory: CommandFactory,
        commandInterpreter: CommandInterpreter,
        variableContext: VariableContext
    ): InterpreterSession {
        return InterpreterSessionImpl(
            userInterface, lexer, substitutor, commandFactory, commandInterpreter, variableContext
        )
    }

}