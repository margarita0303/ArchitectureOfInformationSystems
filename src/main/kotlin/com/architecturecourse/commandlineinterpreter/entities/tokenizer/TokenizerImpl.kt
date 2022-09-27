package com.architecturecourse.commandlineinterpreter.entities.tokenizer

import com.architecturecourse.commandlineinterpreter.entities.lexer.Lexer
import com.architecturecourse.commandlineinterpreter.entities.utils.Token

class TokenizerImpl(private val lexer: Lexer) : Tokenizer {
    override fun tokenize(input: String): List<Token> {
        return lexer.lexInput(input)
    }
}