package com.architecturecourse.commandlineinterpreter.entities.substitutor

import com.architecturecourse.commandlineinterpreter.entities.context.VariableContext
import com.architecturecourse.commandlineinterpreter.entities.lexer.LexerImpl
import com.architecturecourse.commandlineinterpreter.entities.utils.TokenWithQuotes
import com.architecturecourse.commandlineinterpreter.entities.utils.error.QuotesError

class SubstitutorImpl : Substitutor {
    override fun substitute(tokens: List<List<TokenWithQuotes>>, context: VariableContext): String {
        return tokens.joinToString(separator = PIPE.toString()) { section ->
            section.dropWhile{ it.s == ASSIGN_SYMBOL.toString() }.joinToString(separator = getSeparator(section)) {
                substInToken(it, context)
            }
        }
    }

    private fun substInToken(token: TokenWithQuotes, context: VariableContext): String {
        val curVarName = StringBuilder()
        val resToken = StringBuilder()
        var isSingleQuoteOpen = false
        var isDollarOpen = false

        for (c in token.s + DOLLAR_SIGN) {
            if (!isSingleQuoteOpen && isDollarOpen) {
                if (c == DOLLAR_SIGN || c == WHITE_SPACE || c == SINGLE_QUOTE || c == DOUBLE_QUOTE) {
                    isDollarOpen = c == DOLLAR_SIGN
                    val curVarValue =
                        context.getVar(curVarName.toString()) ?: throw Error("bad name : ${curVarName.toString()}")
                    resToken.append(curVarValue)
                    curVarName.clear()
                }

                if (c == SINGLE_QUOTE)
                    isSingleQuoteOpen = true

                if (isDollarOpen) {
                    if (c != DOLLAR_SIGN)
                        curVarName.append(c)
                } else
                    resToken.append(c)

            } else {
                if (!isSingleQuoteOpen && c == DOLLAR_SIGN)
                    isDollarOpen = true

                if (c == SINGLE_QUOTE)
                    isSingleQuoteOpen = !isSingleQuoteOpen

                if (isSingleQuoteOpen || c != DOLLAR_SIGN)
                    resToken.append(c)
            }
        }

        return resToken.toString()
    }

    private fun getSeparator(tokens: List<TokenWithQuotes>): String =
        if (tokens.first().s == ASSIGN_SYMBOL.toString()) ASSIGN_SYMBOL.toString() else WHITE_SPACE.toString()


    companion object {
        private const val ASSIGN_SYMBOL = '='
        private const val WHITE_SPACE = ' '
        private const val SINGLE_QUOTE = '\''
        private const val DOUBLE_QUOTE = '\"'
        private const val DOLLAR_SIGN = '$'
        private const val PIPE = '|'
    }

}