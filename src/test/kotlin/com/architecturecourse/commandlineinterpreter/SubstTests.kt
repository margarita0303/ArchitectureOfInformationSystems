package com.architecturecourse.commandlineinterpreter

import com.architecturecourse.commandlineinterpreter.entities.context.VariableContextImpl
import com.architecturecourse.commandlineinterpreter.entities.substitutor.SubstitutorImpl
import com.architecturecourse.commandlineinterpreter.entities.utils.TokenWithQuotes
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SubstTests {
    @Test
    fun testSubst1() {
        val tokens = listOf(listOf(TokenWithQuotes("\$a\$b")))
        val context = VariableContextImpl()
        context.addOrUpdateVar("a", "ex")
        context.addOrUpdateVar("b", "it")
        val expected = "exit"
        val actual = SubstitutorImpl().substitute(tokens, context)
        assertEquals(expected, actual)
    }

    @Test
    fun testSubst2() {
        val tokens = listOf(listOf(TokenWithQuotes("echo"), TokenWithQuotes("\"\$a\$b\" \$c ' \$d'")))
        val context = VariableContextImpl()
        context.addOrUpdateVar("a", "ex")
        context.addOrUpdateVar("b", "it")
        context.addOrUpdateVar("c", "hu")
        context.addOrUpdateVar("d", "ho")
        val expected = "echo \"exit\" hu ' \$d'"
        val actual = SubstitutorImpl().substitute(tokens, context)
        assertEquals(expected, actual)
    }

    @Test
    fun testSubst3() {
        val tokens = listOf(
            listOf(TokenWithQuotes("="), TokenWithQuotes("var"), TokenWithQuotes("\"\$a\$b\" \$c ' \$d'")),
            listOf(TokenWithQuotes("pwd"))
        )
        val context = VariableContextImpl()
        context.addOrUpdateVar("a", "ex")
        context.addOrUpdateVar("b", "it")
        context.addOrUpdateVar("c", "hu")
        context.addOrUpdateVar("d", "ho")
        val expected = "var=\"exit\" hu ' \$d'|pwd"
        val actual = SubstitutorImpl().substitute(tokens, context)
        assertEquals(expected, actual)
    }
}
