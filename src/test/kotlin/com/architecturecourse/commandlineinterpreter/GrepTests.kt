package com.architecturecourse.commandlineinterpreter

import com.architecturecourse.commandlineinterpreter.entities.command.GrepCommand
import com.architecturecourse.commandlineinterpreter.entities.context.EnvironmentContextImpl
import com.architecturecourse.commandlineinterpreter.entities.context.VariableContextImpl
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GrepTests {
    var pathToTestDirectory = "src/test/kotlin/com/architecturecourse/commandlineinterpreter/"


    @Test
    fun simpleTest() {
        val args = listOf("тор", pathToTestDirectory + "grep_test.txt")
        val expected = "Тор торопился потеряться в штопоре,\n" +
                "но фтор повтором роптал в гроте штопанном"
        val actual = GrepCommand(args).execute(EnvironmentContextImpl(VariableContextImpl())).first.get()
        assertEquals(expected, actual)
    }

    @Test
    fun caseInsensitiveTest() {
        val args = listOf("-i", "тор", pathToTestDirectory + "grep_test.txt")
        val expected = "Тор торопился потеряться в штопоре,\n" +
                "но фтор повтором роптал в гроте штопанном\n" +
                "ТОРГОВЛЯ ТОРТАМИ в Майорке"
        val actual = GrepCommand(args).execute(EnvironmentContextImpl(VariableContextImpl())).first.get()
        assertEquals(expected, actual)
    }

    @Test
    fun linesAfterTest() {
        val args = listOf("-A", "2", "тор", pathToTestDirectory + "grep_test.txt")
        val expected = "Тор торопился потеряться в штопоре,\n" +
                "строка раз\n" +
                "строка два\n" +
                "но фтор повтором роптал в гроте штопанном\n" +
                "строка раз\n" +
                "строка два"
        val actual = GrepCommand(args).execute(EnvironmentContextImpl(VariableContextImpl())).first.get()
        assertEquals(expected, actual)
    }

    @Test
    fun fullWordTest() {
        val args = listOf("-w", "-i", "тор", pathToTestDirectory + "grep_test.txt")
        val expected = "Тор торопился потеряться в штопоре,"
        val actual = GrepCommand(args).execute(EnvironmentContextImpl(VariableContextImpl())).first.get()
        assertEquals(expected, actual)
    }
}