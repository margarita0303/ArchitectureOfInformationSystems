package com.architecturecourse.commandlineinterpreter

import com.architecturecourse.commandlineinterpreter.entities.commandfactory.CommandFactoryImpl
import com.architecturecourse.commandlineinterpreter.entities.utils.error.ExitError
import com.architecturecourse.commandlineinterpreter.entities.utils.error.FileError
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.boot.test.context.SpringBootTest
import java.io.File

@SpringBootTest
class CommandTests {

    /* Testing command echo - must display the entered argument (or arguments) */
    @Test
    fun testExecuteEchoValid() {
        val args = arrayOf("hello", "world")
        val cmdEcho = CommandFactoryImpl().createCommand("echo", args)
        val exp = "hello world"
        val actual = cmdEcho.execute(mutableMapOf())
        Assertions.assertEquals(exp, actual)
    }

    /* Testing command cat [FILE] - must display the contents of the file */
    @Test
    fun testExecuteCatValid() {
        val path = "src/test/kotlin/com/architecturecourse/commandlineinterpreter/ForCommandTests.txt"
        val args = arrayOf(path)
        val cmdCat = CommandFactoryImpl().createCommand("cat", args)
        val exp = "Hello everyone\n" + "How are you?"
        val actual = cmdCat.execute(mutableMapOf())
        Assertions.assertEquals(exp, actual)
    }

    /* Testing command pwd - must print current directory */
    @Test
    fun testExecutePwdValid() {
        val args = arrayOf<String>()
        val cmdPwd = CommandFactoryImpl().createCommand("pwd", args)
        val exp = File("").absolutePath
        val actual = cmdPwd.execute(mutableMapOf())
        Assertions.assertEquals(exp, actual)
    }

    /* Testing command wc [FILE] - must print the number of lines, words and bytes in a file */
    @Test
    fun testExecuteWcValid() {
        val path = "src/test/kotlin/com/architecturecourse/commandlineinterpreter/ForCommandTests.txt"
        val args = arrayOf(path)
        val cmdWc = CommandFactoryImpl().createCommand("wc", args)
        val exp = "lines: 2, words: 5, bytes: 27"
        val actual = cmdWc.execute(mutableMapOf())
        Assertions.assertEquals(exp, actual)
    }

    /* Testing command exit - must throw ExitError */
    @Test
    fun testExecuteExitValid() {
        val args = arrayOf<String>()
        val cmdExit = CommandFactoryImpl().createCommand("exit", args)
        assertThrows<ExitError> { cmdExit.execute(mutableMapOf()) }
    }

    /* Testing errors */

    /* If the file does not exist execute() must throw FileError */
    @Test
    fun testExecuteCatWrongFile() {
        val path = "src/test/kotlin/com/architecturecourse/commandlineinterpreter/aaaa.txt"
        val args = arrayOf(path)
        val cmdCat = CommandFactoryImpl().createCommand("cat", args)
        assertThrows<FileError> { cmdCat.execute(mutableMapOf()) }
    }

    /* If the file does not exist execute() must throw FileError */
    @Test
    fun testExecuteWcWrongFile() {
        val path = "src/test/kotlin/com/architecturecourse/commandlineinterpreter/ForCommandTestss.txt"
        val args = arrayOf(path)
        val cmdWc = CommandFactoryImpl().createCommand("wc", args)
        assertThrows<FileError> { cmdWc.execute(mutableMapOf()) }
    }
}