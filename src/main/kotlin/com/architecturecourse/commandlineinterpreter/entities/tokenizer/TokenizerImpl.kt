package com.architecturecourse.commandlineinterpreter.entities.tokenizer

import com.architecturecourse.commandlineinterpreter.entities.context.VariableContext
import com.architecturecourse.commandlineinterpreter.entities.lexer.Lexer
import com.architecturecourse.commandlineinterpreter.entities.substitutor.Substitutor
import com.architecturecourse.commandlineinterpreter.entities.utils.Token

class TokenizerImpl(private val lexer: Lexer, private  val substitutor: Substitutor, private val context: VariableContext) : Tokenizer {
    override fun tokenize(input: String): List<List<Token>> {
        val tokensBeforeSubst =  lexer.lexInput(input)
        val substInput = substitutor.substitute(tokensBeforeSubst, context)
        return lexer.lexAfterSubst(substInput)
    }
}