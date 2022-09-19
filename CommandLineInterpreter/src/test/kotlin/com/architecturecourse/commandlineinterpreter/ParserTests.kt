package com.architecturecourse.commandlineinterpreter

import com.architecturecourse.commandlineinterpreter.entities.parser.ParserImpl
import com.architecturecourse.commandlineinterpreter.entities.utils.Arg
import com.architecturecourse.commandlineinterpreter.entities.utils.CmdCategory
import com.architecturecourse.commandlineinterpreter.entities.utils.CmdType
import com.architecturecourse.commandlineinterpreter.entities.utils.Token
import com.architecturecourse.commandlineinterpreter.entities.utils.error.EmptyTokenListError
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ParserTests {
    @Test
    fun testParseAssign() {
        val tokens = listOf(Token("a"), Token("helloba seworld=cool bruh")) to CmdCategory.Assign
        val exp = CmdType.Assign to listOf(Arg("a"), Arg("helloba seworld=cool bruh"))
        val actual = ParserImpl().parse(tokens)
        Assertions.assertEquals(exp, actual)
    }
    
    @Test
    fun testParseEcho() {
        val tokens = listOf(
            Token("echo"), Token("file.txt gtgt"),
            Token(" ' ' =opop"), Token("tutu")
        ) to CmdCategory.Invoke
        val exp =
            CmdType.Echo to listOf(
                Arg("file.txt gtgt"),
                Arg(" ' ' =opop"), Arg("tutu")
            )
        val actual = ParserImpl().parse(tokens)
        Assertions.assertEquals(exp, actual)
    }

    @Test
    fun testParseExit() {
        val tokens = listOf(Token("exit")) to CmdCategory.Invoke
        val exp = CmdType.Exit to listOf<Arg>()
        val actual = ParserImpl().parse(tokens)
        Assertions.assertEquals(exp, actual)
    }

    @Test
    fun testParseWc() {
        val tokens = listOf(Token("wc"), Token("file.txt")) to CmdCategory.Invoke
        val exp = CmdType.Wc to listOf(Arg("file.txt"))
        val actual = ParserImpl().parse(tokens)
        Assertions.assertEquals(exp, actual)
    }
    @Test
    fun testParseEmptyTokensList() {
        val tokens = listOf<Token>()
        assertThrows<EmptyTokenListError> { ParserImpl().parse(tokens to CmdCategory.Invoke) }
    }
}