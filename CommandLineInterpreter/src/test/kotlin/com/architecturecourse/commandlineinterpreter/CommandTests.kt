package com.architecturecourse.commandlineinterpreter

import com.architecturecourse.commandlineinterpreter.entities.commandfactory.CommandFactoryImpl
import com.architecturecourse.commandlineinterpreter.entities.context.VariableContextImpl
import com.architecturecourse.commandlineinterpreter.entities.utils.Arg
import com.architecturecourse.commandlineinterpreter.entities.utils.CmdType
import com.architecturecourse.commandlineinterpreter.entities.utils.error.ExitError
import com.architecturecourse.commandlineinterpreter.entities.utils.error.FileError
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.boot.test.context.SpringBootTest
import java.io.File
import java.util.*

@SpringBootTest
class CommandTests {
    private val isWindows = System.getProperty("os.name")
        .lowercase(Locale.getDefault()).startsWith("windows")

    var pathToTestDirectory = "src/test/kotlin/com/architecturecourse/commandlineinterpreter/"

    /* Testing command echo - must display the entered argument (or arguments) */
    @Test
    fun testExecuteEchoValid() {
        val args = arrayOf("hello", "world").map { Arg(it) }
        val cmdEcho = CommandFactoryImpl().createCommand(CmdType.Echo, args)
        val expected = "hello world" to 0
        val actual = cmdEcho.execute(VariableContextImpl())
        Assertions.assertEquals(expected, actual)
    }

    /* Testing command cat [FILE] - must display the contents of the file */
    @Test
    fun testExecuteCatValid() {
        val path = pathToTestDirectory + "ForCommandTests.txt"
        val args = arrayOf(path).map { Arg(it) }
        val cmdCat = CommandFactoryImpl().createCommand(CmdType.Cat, args)
        val expected = "Hello everyone\n" + "How are you?" to 0
        val actual = cmdCat.execute(VariableContextImpl())
        Assertions.assertEquals(expected, actual)
    }

    /* Testing command pwd - must print current directory */
    @Test
    fun testExecutePwdValid() {
        val args = listOf<Arg>()
        val cmdPwd = CommandFactoryImpl().createCommand(CmdType.Pwd, args)
        val expected = File("").absolutePath to 0
        val actual = cmdPwd.execute(VariableContextImpl())
        Assertions.assertEquals(expected, actual)
    }

    /* Testing command wc [FILE] - must print the number of lines, words and bytes in a file */
    @Test
    fun testExecuteWcValid() {
        val path = pathToTestDirectory + "ForCommandTests.txt"
        val args = arrayOf(path).map { Arg(it) }
        val cmdWc = CommandFactoryImpl().createCommand(CmdType.Wc, args)
        val expected = "lines: 2, words: 5, bytes: 27" to 0
        val actual = cmdWc.execute(VariableContextImpl())
        Assertions.assertEquals(expected, actual)
    }

    /* Testing command exit - must throw ExitError */
    @Test
    fun testExecuteExitValid() {
        val args = listOf<Arg>()
        val cmdExit = CommandFactoryImpl().createCommand(CmdType.Exit, args)
        assertThrows<ExitError> { cmdExit.execute(VariableContextImpl()) }
    }

    /* Testing errors */

    /* If the file does not exist execute() must throw FileError */
    @Test
    fun testExecuteCatWrongFile() {
        val path = pathToTestDirectory + "WrongFileName.txt"
        val args = arrayOf(path).map { Arg(it) }
        val cmdCat = CommandFactoryImpl().createCommand(CmdType.Cat, args)
        assertThrows<FileError> { cmdCat.execute(VariableContextImpl()) }
    }

    /* If the file does not exist execute() must throw FileError */
    @Test
    fun testExecuteWcWrongFile() {
        val path = pathToTestDirectory + "WrongFileName.txt"
        val args = arrayOf(path).map { Arg(it) }
        val cmdWc = CommandFactoryImpl().createCommand(CmdType.Wc, args)
        assertThrows<FileError> { cmdWc.execute(VariableContextImpl()) }
    }

    // TODO Add Linux test case
    @Test
    fun testUnknownCommand() {
        if (isWindows) {
            val args = listOf("echo", "42").map { Arg(it) }
            val cmdUnk = CommandFactoryImpl().createCommand(CmdType.Unknown, args)
            val expected = "42" to 0
            Assertions.assertEquals(expected, cmdUnk.execute(VariableContextImpl()))
        }
    }
}