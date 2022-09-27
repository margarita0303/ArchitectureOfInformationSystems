package com.architecturecourse.commandlineinterpreter

import com.architecturecourse.commandlineinterpreter.entities.parser.ParserImpl
import com.architecturecourse.commandlineinterpreter.entities.utils.Arg
import com.architecturecourse.commandlineinterpreter.entities.utils.CommandData
import com.architecturecourse.commandlineinterpreter.entities.utils.CommandType
import com.architecturecourse.commandlineinterpreter.entities.utils.Token
import com.architecturecourse.commandlineinterpreter.entities.utils.error.EmptyTokenListError
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ParserTests {
    @Test
    fun testParseAssign() {
        val tokens = listOf(Token("="), Token("a"), Token("helloba seworld=cool bruh"))
        val exp = CommandData(
            CommandType.Assign, listOf(Arg("a"), Arg("helloba seworld=cool bruh"))
        )
        val actual = ParserImpl().parse(tokens)
        Assertions.assertEquals(exp, actual)
    }

    @Test
    fun testParseEcho() {
        val tokens = listOf(
            Token("echo"), Token("file.txt gtgt"),
            Token(" ' ' =opop"), Token("tutu")
        )
        val exp = CommandData(
            CommandType.Echo, listOf(
                Arg("file.txt gtgt"),
                Arg(" ' ' =opop"), Arg("tutu")
            )
        )
        val actual = ParserImpl().parse(tokens)
        Assertions.assertEquals(exp, actual)
    }

    @Test
    fun testParseExit() {
        val tokens = listOf(Token("exit"))
        val exp = CommandData(CommandType.Exit, listOf())
        val actual = ParserImpl().parse(tokens)
        Assertions.assertEquals(exp, actual)
    }

    @Test
    fun testParseWc() {
        val tokens = listOf(Token("wc"), Token("file.txt"))
        val exp = CommandData(CommandType.Wc, listOf(Arg("file.txt")))
        val actual = ParserImpl().parse(tokens)
        Assertions.assertEquals(exp, actual)
    }

    @Test
    fun testParseEmptyTokensList() {
        val tokens = listOf<Token>()
        assertThrows<EmptyTokenListError> { ParserImpl().parse(tokens) }
    }
}