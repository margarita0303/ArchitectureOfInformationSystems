package com.architecturecourse.commandlineinterpreter.configurations

import com.architecturecourse.commandlineinterpreter.entities.context.EnvironmentContext
import com.architecturecourse.commandlineinterpreter.entities.context.EnvironmentContextImpl
import com.architecturecourse.commandlineinterpreter.entities.context.VariableContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class EnvironmentContextConfiguration {

    @Bean
    fun environmentContext(variableContext: VariableContext): EnvironmentContext {
        return EnvironmentContextImpl(variableContext)
    }

}