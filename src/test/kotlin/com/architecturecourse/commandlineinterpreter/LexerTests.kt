package com.architecturecourse.commandlineinterpreter

import com.architecturecourse.commandlineinterpreter.entities.lexer.LexerImpl
import com.architecturecourse.commandlineinterpreter.entities.utils.Token
import com.architecturecourse.commandlineinterpreter.entities.utils.TokenWithQuotes
import com.architecturecourse.commandlineinterpreter.entities.utils.error.FirstTokenError
import com.architecturecourse.commandlineinterpreter.entities.utils.error.QuotesError
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LexerTests {
    @Test
    fun testLexAssignValid() {
        val input = "a=hello'ba se'world=cool bruh"
        val exp =
            listOf(listOf(TokenWithQuotes("="), TokenWithQuotes("a"), TokenWithQuotes("hello'ba se'world=cool bruh")))
        val actual = LexerImpl().lexInput(input)
        assertEquals(exp, actual)
    }

    @Test
    fun testLexAssignInvalid() {
        val input = "'a ba'='hello'"
        assertThrows<FirstTokenError> { LexerImpl().lexInput(input) }
    }

    @Test
    fun testLexInvokeValid() {
        val input = "echo \"file.txt gtgt\" \" \' \' =opop\" tutu"
        val exp =
            listOf(
                listOf(
                    TokenWithQuotes("echo"), TokenWithQuotes("\"file.txt gtgt\""),
                    TokenWithQuotes("\" ' ' =opop\""), TokenWithQuotes("tutu")
                )
            )
        val actual = LexerImpl().lexInput(input)
        assertEquals(exp, actual)
    }

    @Test
    fun testLexInvokeInvalid() {
        val input = "'p wd' 'hello=poll' 'okokok'"
        assertThrows<FirstTokenError> { LexerImpl().lexInput(input) }
    }

    @Test
    fun testQuotesAreNotClosed() {
        val input = "echo \'file.txt"
        assertThrows<QuotesError> { LexerImpl().lexInput(input) }
    }


    @Test
    fun testPipes() {
        val input = "echo \"123 \$a\" | cat | wc "
        val exp =
            listOf(
                listOf(TokenWithQuotes(s = "echo"), TokenWithQuotes(s = "\"123 \$a\"")),
                listOf(TokenWithQuotes(s = "cat")),
                listOf(TokenWithQuotes(s = "wc"))
            )
        val actual = LexerImpl().lexInput(input)
        assertEquals(exp, actual)
    }

    @Test
    fun testLexAssignValidAfterSubst() {
        val input = "a=hello'ba se'world=cool bruh"
        val exp =
            listOf(listOf(Token("="), Token("a"), Token("helloba seworld=cool bruh")))
        val actual = LexerImpl().lexAfterSubst(input)
        assertEquals(exp, actual)
    }

    @Test
    fun testLexInvokeValidAfterSubst() {
        val input = "echo \"file.txt gtgt\" \" \' \' =opop\" tutu"
        val exp =
            listOf(
                listOf(
                    Token("echo"), Token("file.txt gtgt"),
                    Token(" ' ' =opop"), Token("tutu")
                )
            )
        val actual = LexerImpl().lexAfterSubst(input)
        assertEquals(exp, actual)
    }
}