package com.architecturecourse.commandlineinterpreter.configurations

import com.architecturecourse.commandlineinterpreter.entities.parser.Parser
import com.architecturecourse.commandlineinterpreter.entities.parser.ParserImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ParserConfiguration {
    @Bean
    fun parser(): Parser {
        return ParserImpl()
    }
}