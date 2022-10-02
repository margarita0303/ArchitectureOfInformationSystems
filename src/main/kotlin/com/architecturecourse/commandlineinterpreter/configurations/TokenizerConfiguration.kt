package com.architecturecourse.commandlineinterpreter.configurations

import com.architecturecourse.commandlineinterpreter.entities.context.VariableContext
import com.architecturecourse.commandlineinterpreter.entities.lexer.Lexer
import com.architecturecourse.commandlineinterpreter.entities.substitutor.Substitutor
import com.architecturecourse.commandlineinterpreter.entities.tokenizer.TokenizerImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class TokenizerConfiguration {

    @Bean
    fun tokenizer(lexer: Lexer, substitutor: Substitutor, context: VariableContext): TokenizerImpl {
        return TokenizerImpl(lexer, substitutor, context);
    }
}