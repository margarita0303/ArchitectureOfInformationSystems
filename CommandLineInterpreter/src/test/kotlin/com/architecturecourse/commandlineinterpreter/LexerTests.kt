package com.architecturecourse.commandlineinterpreter

import com.architecturecourse.commandlineinterpreter.components.lexer.LexerImpl
import com.architecturecourse.commandlineinterpreter.components.utils.CmdCategory
import com.architecturecourse.commandlineinterpreter.components.utils.Token
import com.architecturecourse.commandlineinterpreter.components.utils.error.EmptyInputError
import com.architecturecourse.commandlineinterpreter.components.utils.error.FirstTokenError
import com.architecturecourse.commandlineinterpreter.components.utils.error.QuotesError
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class LexerTests {
    @Test
    fun testLexAssignValid() {
        val input = "a=hello'ba se'world=cool bruh"
        val exp = listOf(Token("a"), Token("helloba seworld=cool bruh")) to CmdCategory.Assign
        val actual = LexerImpl().lex(input)
        assertEquals(exp, actual)
    }

    @Test
    fun testLexAssignInvalid() {
        val input = "'a ba'='hello'"
        assertThrows<FirstTokenError> { LexerImpl().lex(input) }
    }

    @Test
    fun testLexInvokeValid() {
        val input = "echo \"file.txt gtgt\" \" \' \' =opop\" tutu"
        val exp =
            listOf(
                Token("echo"), Token("file.txt gtgt"),
                Token(" ' ' =opop"), Token("tutu")
            ) to CmdCategory.Invoke
        val actual = LexerImpl().lex(input)
        assertEquals(exp, actual)
    }

    @Test
    fun testLexInvokeInvalid() {
        val input = "'p wd' 'hello=poll' 'okokok'"
        assertThrows<FirstTokenError> { LexerImpl().lex(input) }
    }

    @Test
    fun testQuotesAreNotClosed() {
        val input = "echo \'file.txt"
        assertThrows<QuotesError> { LexerImpl().lex(input) }
    }

    @Test
    fun testEmptyInput() {
        val input = ""
        assertThrows<EmptyInputError> { LexerImpl().lex(input) }
    }
}