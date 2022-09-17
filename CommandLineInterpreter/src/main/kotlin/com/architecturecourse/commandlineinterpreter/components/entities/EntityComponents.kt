package com.architecturecourse.commandlineinterpreter.components.entities

import com.architecturecourse.commandlineinterpreter.components.userinterface.UserInterface
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
class EntityComponents {

    @Bean
    fun userInterface(): UserInterface {
        return UserInterface()
    }
}