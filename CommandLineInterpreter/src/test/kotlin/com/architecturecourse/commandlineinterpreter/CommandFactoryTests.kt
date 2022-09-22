package com.architecturecourse.commandlineinterpreter

import com.architecturecourse.commandlineinterpreter.entities.commandfactory.CommandFactoryImpl
import com.architecturecourse.commandlineinterpreter.entities.utils.error.NotEnoughArgumentsError
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CommandFactoryTests {
    /* If for command "cat" the argument list is empty must throw NotEnoughArgumentsError */
    @Test
    fun testCreateCatWithNoArguments() {
        val args = emptyArray<String>()
        assertThrows<NotEnoughArgumentsError> { CommandFactoryImpl().createCommand("cat", args) }
    }

    /* If for command "wc" the argument list is empty must throw NotEnoughArgumentsError */
    @Test
    fun testCreateWcWithNoArguments() {
        val args = emptyArray<String>()
        assertThrows<NotEnoughArgumentsError> { CommandFactoryImpl().createCommand("wc", args) }
    }
}