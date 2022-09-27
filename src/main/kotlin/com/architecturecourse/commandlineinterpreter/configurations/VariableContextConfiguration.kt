package com.architecturecourse.commandlineinterpreter.configurations

import com.architecturecourse.commandlineinterpreter.entities.context.VariableContext
import com.architecturecourse.commandlineinterpreter.entities.context.VariableContextImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class VariableContextConfiguration {
    @Bean
    fun variableContext(): VariableContext {
        return VariableContextImpl()
    }
}