package com.architecturecourse.commandlineinterpreter.entities.lexer

import com.architecturecourse.commandlineinterpreter.entities.utils.Token

/* Converts input string to token list for further parsing */
interface Lexer {
    fun lexInput(input: String): List<Token>
}