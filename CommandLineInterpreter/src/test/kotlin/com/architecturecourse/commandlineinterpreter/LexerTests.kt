package com.architecturecourse.commandlineinterpreter

import com.architecturecourse.commandlineinterpreter.entities.lexer.LexerImpl
import com.architecturecourse.commandlineinterpreter.entities.utils.Token
import com.architecturecourse.commandlineinterpreter.entities.utils.error.EmptyInputError
import com.architecturecourse.commandlineinterpreter.entities.utils.error.FirstTokenError
import com.architecturecourse.commandlineinterpreter.entities.utils.error.QuotesError
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class LexerTests {
    @Test
    fun testLexAssignValid() {
        val input = "a=hello'ba se'world=cool bruh"
        val exp = listOf(Token("="), Token("a"), Token("helloba seworld=cool bruh"))
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
                Token("echo"), Token("file.txt gtgt"),
                Token(" ' ' =opop"), Token("tutu")
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
    fun testEmptyInput() {
        val input = ""
        assertThrows<EmptyInputError> { LexerImpl().lexInput(input) }
    }
}