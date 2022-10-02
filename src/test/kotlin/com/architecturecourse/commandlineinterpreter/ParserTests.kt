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
        val tokens = listOf(listOf(Token("="), Token("a"), Token("helloba seworld=cool bruh")))
        val exp = listOf(CommandData(
            CommandType.Assign, listOf(Arg("a"), Arg("helloba seworld=cool bruh"))
        ))
        val actual = ParserImpl().parse(tokens)
        Assertions.assertEquals(exp, actual)
    }

    @Test
    fun testParseEcho() {
        val tokens = listOf(listOf(
            Token("echo"), Token("file.txt gtgt"),
            Token(" ' ' =opop"), Token("tutu")
        ))
        val exp = listOf(CommandData(
            CommandType.Echo, listOf(
                Arg("file.txt gtgt"),
                Arg(" ' ' =opop"), Arg("tutu")
            )
        ))
        val actual = ParserImpl().parse(tokens)
        Assertions.assertEquals(exp, actual)
    }

    @Test
    fun testParseExit() {
        val tokens = listOf(listOf(Token("exit")))
        val exp = listOf(CommandData(CommandType.Exit, listOf()))
        val actual = ParserImpl().parse(tokens)
        Assertions.assertEquals(exp, actual)
    }

    @Test
    fun testParseWc() {
        val tokens = listOf(listOf(Token("wc"), Token("file.txt")))
        val exp = listOf(CommandData(CommandType.Wc, listOf(Arg("file.txt"))))
        val actual = ParserImpl().parse(tokens)
        Assertions.assertEquals(exp, actual)
    }

    @Test
    fun testParseEmptyTokensList() {
        val tokens = listOf<List<Token>>()
        assertThrows<EmptyTokenListError> { ParserImpl().parse(tokens) }
    }
}