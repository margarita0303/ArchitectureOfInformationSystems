package com.architecturecourse.commandlineinterpreter.entities.tokenizer

import com.architecturecourse.commandlineinterpreter.entities.utils.Token

interface Tokenizer {
    fun tokenize(input: String): List<Token>
}