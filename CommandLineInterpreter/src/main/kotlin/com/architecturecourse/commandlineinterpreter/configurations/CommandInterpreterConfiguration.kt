package com.architecturecourse.commandlineinterpreter.configurations

import com.architecturecourse.commandlineinterpreter.entities.commandinterpreter.CommandInterpreter
import com.architecturecourse.commandlineinterpreter.entities.commandinterpreter.CommandInterpreterImpl
import com.architecturecourse.commandlineinterpreter.entities.context.VariableContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CommandInterpreterConfiguration {

    @Bean
    fun commandInterpreter(context: VariableContext): CommandInterpreter {
        return CommandInterpreterImpl(context)
    }
}