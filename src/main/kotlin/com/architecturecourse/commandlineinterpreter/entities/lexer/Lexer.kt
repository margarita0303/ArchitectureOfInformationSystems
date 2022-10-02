package com.architecturecourse.commandlineinterpreter.entities.lexer

import com.architecturecourse.commandlineinterpreter.entities.utils.Token
import com.architecturecourse.commandlineinterpreter.entities.utils.TokenWithQuotes

/* Converts input string to token list for further parsing */
interface Lexer {
    fun lexInput(input: String): List<List<TokenWithQuotes>>
    fun lexAfterSubst(substInput: String): List<List<Token>>
}