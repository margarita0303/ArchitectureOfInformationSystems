package com.architecturecourse.commandlineinterpreter.configurations

import com.architecturecourse.commandlineinterpreter.entities.userinterface.UserInterface
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class UserInterfaceConfiguration {

    @Bean
    fun userInterface(): UserInterface {
        return UserInterface()
    }
}