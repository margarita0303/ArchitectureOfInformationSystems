package com.architecturecourse.commandlineinterpreter.configurations

import com.architecturecourse.commandlineinterpreter.entities.lexer.Lexer
import com.architecturecourse.commandlineinterpreter.entities.tokenizer.TokenizerImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class TokenizerConfiguration {

    @Bean
    fun tokenizer(lexer: Lexer): TokenizerImpl {
        return TokenizerImpl(lexer);
    }
}