package com.architecturecourse.commandlineinterpreter.configurations

import com.architecturecourse.commandlineinterpreter.entities.commandinterpreter.CommandInterpreter
import com.architecturecourse.commandlineinterpreter.entities.commandinterpreter.CommandInterpreterImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CommandInterpreterConfiguration {

    @Bean
    fun commandInterpreter(): CommandInterpreter {
        return CommandInterpreterImpl()
    }
}