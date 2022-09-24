package com.architecturecourse.commandlineinterpreter

import com.architecturecourse.commandlineinterpreter.entities.commandfactory.CommandFactoryImpl
import com.architecturecourse.commandlineinterpreter.entities.utils.Arg
import com.architecturecourse.commandlineinterpreter.entities.utils.CmdType
import com.architecturecourse.commandlineinterpreter.entities.utils.error.WrongNumberOfArgumentsError
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CommandFactoryTests {
    /* If for command "cat" the argument list is empty must throw WrongNumberOfArgumentsError */
    @Test
    fun testCreateCatWithNoArguments() {
        val args = emptyList<Arg>()
        assertThrows<WrongNumberOfArgumentsError> { CommandFactoryImpl().createCommand(CmdType.Cat, args) }
    }

    /* If for command "wc" the argument list is empty must throw WrongNumberOfArgumentsError */
    @Test
    fun testCreateWcWithNoArguments() {
        val args = emptyList<Arg>()
        assertThrows<WrongNumberOfArgumentsError> { CommandFactoryImpl().createCommand(CmdType.Wc, args) }
    }
}