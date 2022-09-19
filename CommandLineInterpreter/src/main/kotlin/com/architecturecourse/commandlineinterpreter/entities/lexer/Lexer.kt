package com.architecturecourse.commandlineinterpreter.entities.lexer

import com.architecturecourse.commandlineinterpreter.entities.utils.Token

interface Lexer {
    fun lexInput(input: String): List<Token>
}