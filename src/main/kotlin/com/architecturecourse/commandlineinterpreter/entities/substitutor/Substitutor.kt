package com.architecturecourse.commandlineinterpreter.entities.substitutor

import com.architecturecourse.commandlineinterpreter.entities.context.VariableContext
import com.architecturecourse.commandlineinterpreter.entities.utils.TokenWithQuotes

/* TODO: next hw */
interface Substitutor {
    fun substitute(tokens: List<List<TokenWithQuotes>>, context: VariableContext) : String
}