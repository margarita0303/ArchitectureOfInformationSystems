package com.architecturecourse.commandlineinterpreter.configurations

import com.architecturecourse.commandlineinterpreter.entities.substitutor.Substitutor
import com.architecturecourse.commandlineinterpreter.entities.substitutor.SubstitutorImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SubstitutorConfiguration {

    @Bean
    fun substitutor(): Substitutor {
        return SubstitutorImpl()
    }
}