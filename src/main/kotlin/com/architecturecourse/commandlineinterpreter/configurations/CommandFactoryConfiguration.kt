package com.architecturecourse.commandlineinterpreter.configurations

import com.architecturecourse.commandlineinterpreter.entities.commandfactory.CommandFactory
import com.architecturecourse.commandlineinterpreter.entities.commandfactory.CommandFactoryImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CommandFactoryConfiguration {
    @Bean
    fun commandFactory(): CommandFactory {
        return CommandFactoryImpl()
    }
}