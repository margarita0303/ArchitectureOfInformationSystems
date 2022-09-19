package com.architecturecourse.commandlineinterpreter.entities.lexer

import com.architecturecourse.commandlineinterpreter.entities.utils.CmdCategory
import com.architecturecourse.commandlineinterpreter.entities.utils.Token

interface Lexer {
    fun lex(input: String): Pair<List<Token>, CmdCategory>
}