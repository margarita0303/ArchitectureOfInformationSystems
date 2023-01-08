package com.architecturecourse.commandlineinterpreter.entities.lexer

import com.architecturecourse.commandlineinterpreter.entities.utils.CommandType
import com.architecturecourse.commandlineinterpreter.entities.utils.Token
import com.architecturecourse.commandlineinterpreter.entities.utils.TokenWithQuotes
import com.architecturecourse.commandlineinterpreter.entities.utils.error.EmptyInputError
import com.architecturecourse.commandlineinterpreter.entities.utils.error.FirstTokenError
import com.architecturecourse.commandlineinterpreter.entities.utils.error.QuotesError

class LexerImpl : Lexer {
    override fun lexInput(input: String): List<List<TokenWithQuotes>> {
        return splitByPipes(input).map { section ->
            processLex(
                section.trim(),
                removeQuotes = false
            ).map { TokenWithQuotes(it) }
        }
    }

    override fun lexAfterSubst(substInput: String): List<List<Token>> {
        return splitByPipes(substInput).map { section ->
            processLex(
                section.trim(),
                removeQuotes = true
            ).map { Token(it) }
        }
    }

    private fun splitByPipes(input: String): List<String> {
        return splitOutsideQuotes(input, PIPE, onlyFirst = false, removeQuotes = false)
    }


    private fun processLex(input: String, removeQuotes: Boolean): List<String> {
        if (input.isEmpty()) throw EmptyInputError
        val splitByAssign = splitOutsideQuotes(input, ASSIGN_SYMBOL, onlyFirst = true, removeQuotes = removeQuotes)
        if (splitByAssign.size == 2) {
            checkFirstToken(splitByAssign)
            return listOf(CommandType.Assign.TAG) + splitByAssign
        }
        val splitBySpace = splitOutsideQuotes(
            input, WHITE_SPACE, onlyFirst = false, removeQuotes = removeQuotes
        )
        checkFirstToken(splitBySpace)
        return splitBySpace
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
        if (isSingleQuoteOpen || isDoubleQuoteOpen) throw QuotesError

        if (curToken.isNotEmpty()) tokens.add(curToken.toString())

        return tokens
    }

    private fun checkFirstToken(tokens: List<String>) {
        if (tokens.first().any { c -> c == ASSIGN_SYMBOL || c == WHITE_SPACE }) throw FirstTokenError
    }


    companion object {
        private const val ASSIGN_SYMBOL = '='
        private const val WHITE_SPACE = ' '
        private const val SINGLE_QUOTE = '\''
        private const val DOUBLE_QUOTE = '\"'
        private const val PIPE = '|'
    }
}