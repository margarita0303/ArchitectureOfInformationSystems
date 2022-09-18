package com.architecturecourse.commandlineinterpreter.components.lexer

import Lexer
import com.architecturecourse.commandlineinterpreter.components.utils.CmdCategory
import com.architecturecourse.commandlineinterpreter.components.utils.Token
import com.architecturecourse.commandlineinterpreter.components.utils.error.EmptyInputError
import com.architecturecourse.commandlineinterpreter.components.utils.error.FirstTokenError
import com.architecturecourse.commandlineinterpreter.components.utils.error.QuotesError

class LexerImpl : Lexer {
    override fun lex(input: String): Pair<List<Token>, CmdCategory> {
        val (tokens, cat) = processLex(input, removeQuotes = true)
        return tokens.map { Token(it) } to cat
    }

    private fun processLex(input: String, removeQuotes: Boolean): Pair<List<String>, CmdCategory> {
        if (input.isEmpty())
            throw EmptyInputError
        val splitByAssign = splitOutsideQuotes(input, ASSIGN_SYMBOL, onlyFirst = true, removeQuotes = removeQuotes)
        if (splitByAssign.size == 2) {
            checkFirstToken(splitByAssign)
            return splitByAssign to CmdCategory.Assign
        }
        val splitBySpace = splitOutsideQuotes(
            input,
            WHITE_SPACE,
            onlyFirst = false,
            removeQuotes = removeQuotes
        )
        checkFirstToken(splitBySpace)
        return splitBySpace to CmdCategory.Invoke
    }

    private fun splitOutsideQuotes(s: String, delim: Char, onlyFirst: Boolean, removeQuotes: Boolean): List<String> {
        val curToken = StringBuilder()
        val tokens = mutableListOf<String>()
        var isSingleQuoteOpen = false
        var isDoubleQuoteOpen = false

        for (c in s) {
            if (!isSingleQuoteOpen && !isDoubleQuoteOpen) {
                when {
                    c == delim && curToken.isNotEmpty() && (!onlyFirst || tokens.isEmpty()) -> {
                        tokens.add(curToken.toString())
                        curToken.clear()
                    }

                    else -> {
                        if (c == SINGLE_QUOTE) {
                            isSingleQuoteOpen = true
                            if (removeQuotes) continue
                        }
                        if (c == DOUBLE_QUOTE) {
                            isDoubleQuoteOpen = true
                            if (removeQuotes) continue
                        }
                        curToken.append(c)
                    }
                }
            } else {
                if (c == SINGLE_QUOTE && isSingleQuoteOpen) {
                    isSingleQuoteOpen = false
                    if (removeQuotes) continue
                }
                if (c == DOUBLE_QUOTE && isDoubleQuoteOpen) {
                    isDoubleQuoteOpen = false
                    if (removeQuotes) continue
                }
                curToken.append(c)
            }
        }
        if (isSingleQuoteOpen || isDoubleQuoteOpen)
            throw QuotesError

        if (curToken.isNotEmpty())
            tokens.add(curToken.toString())

        return tokens
    }

    private fun checkFirstToken(tokens: List<String>) {
        if (tokens.first().any { c -> c == ASSIGN_SYMBOL || c == WHITE_SPACE })
            throw FirstTokenError
    }


    companion object {
        private const val ASSIGN_SYMBOL = '=';
        private const val WHITE_SPACE = ' ';
        private const val SINGLE_QUOTE = '\''
        private const val DOUBLE_QUOTE = '\"'
    }
}