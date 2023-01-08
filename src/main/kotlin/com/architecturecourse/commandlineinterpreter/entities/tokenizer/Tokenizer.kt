package com.architecturecourse.commandlineinterpreter.entities.tokenizer

import com.architecturecourse.commandlineinterpreter.entities.utils.Token

/* Manages Lexer and Substitutor workflow. Will make sense when Substitutor appear */
interface Tokenizer {
    fun tokenize(input: String): List<List<Token>>
}