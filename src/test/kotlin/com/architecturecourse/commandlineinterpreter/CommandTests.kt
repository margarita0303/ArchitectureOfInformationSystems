package com.architecturecourse.commandlineinterpreter

import com.architecturecourse.commandlineinterpreter.entities.commandfactory.CommandFactoryImpl
import com.architecturecourse.commandlineinterpreter.entities.context.EnvironmentContextImpl
import com.architecturecourse.commandlineinterpreter.entities.context.VariableContextImpl
import com.architecturecourse.commandlineinterpreter.entities.utils.Arg
import com.architecturecourse.commandlineinterpreter.entities.utils.CommandType
import com.architecturecourse.commandlineinterpreter.entities.utils.error.FileError
import com.architecturecourse.commandlineinterpreter.entities.utils.error.PathError
import com.architecturecourse.commandlineinterpreter.entities.utils.exit.ExitInterruption
import java.io.File
import java.util.Optional
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CommandTests {
    var pathToTestDirectory = "src/test/kotlin/com/architecturecourse/commandlineinterpreter/"

    /* Testing command echo - must display the entered argument (or arguments) */
    @Test
    fun testExecuteEchoValid() {
        val args = arrayOf("hello", "world").map { Arg(it) }
        val cmdEcho = CommandFactoryImpl().createCommand(CommandType.Echo, args)
        val expected = Optional.of("hello world") to 0
        val actual = cmdEcho.execute(EnvironmentContextImpl(VariableContextImpl()))
        Assertions.assertEquals(expected, actual)
    }

    /* Testing command cat [FILE] - must display the contents of the file */
    @Test
    fun testExecuteCatValid() {
        val path = pathToTestDirectory + "ForCommandTests.txt"
        val args = arrayOf(path).map { Arg(it) }
        val cmdCat = CommandFactoryImpl().createCommand(CommandType.Cat, args)
        val expected = Optional.of("Hello everyone") to 0
        val actual = cmdCat.execute(EnvironmentContextImpl(VariableContextImpl()))
        Assertions.assertEquals(expected, actual)
    }

    /* Testing command pwd - must print current directory */
    @Test
    fun testExecutePwdValid() {
        val args = listOf<Arg>()
        val cmdPwd = CommandFactoryImpl().createCommand(CommandType.Pwd, args)
        val expected = Optional.of(File("").absolutePath) to 0
        val actual = cmdPwd.execute(EnvironmentContextImpl(VariableContextImpl()))
        Assertions.assertEquals(expected, actual)
    }

    /* Testing command wc [FILE] - must print the number of lines, words and bytes in a file */
    @Test
    fun testExecuteWcValid() {
        val path = pathToTestDirectory + "ForCommandTests.txt"
        val args = arrayOf(path).map { Arg(it) }
        val cmdWc = CommandFactoryImpl().createCommand(CommandType.Wc, args)
        val expected = Optional.of("lines: 1, words: 2, bytes: 14") to 0
        val actual = cmdWc.execute(EnvironmentContextImpl(VariableContextImpl()))
        Assertions.assertEquals(expected, actual)
    }

    /* Testing command exit - must throw ExitError */
    @Test
    fun testExecuteExitValid() {
        val args = listOf<Arg>()
        val cmdExit = CommandFactoryImpl().createCommand(CommandType.Exit, args)
        assertThrows<ExitInterruption> { cmdExit.execute(EnvironmentContextImpl(VariableContextImpl())) }
    }

    /* Testing command ls - must print files and folders are contained in a specified folder */
    @Test
    fun testExecuteLsValid() {
        val path = pathToTestDirectory + "ls_test"
        val args = listOf(path).map { Arg(it) }
        val cmdLs = CommandFactoryImpl().createCommand(CommandType.Ls, args)
        val expected = listOf("file_1.txt", "file_2", "folder" + File.separator).sorted()
        val (actual, exitCode) = cmdLs.execute(EnvironmentContextImpl(VariableContextImpl()))
        Assertions.assertEquals(0, exitCode)
        Assertions.assertLinesMatch(
            expected,
            actual.get().lines().sorted()
        )
    }

    /* Testing errors */

    /* If the file does not exist execute() must throw FileError */
    @Test
    fun testExecuteCatWrongFile() {
        val path = pathToTestDirectory + "WrongFileName.txt"
        val args = arrayOf(path).map { Arg(it) }
        val cmdCat = CommandFactoryImpl().createCommand(CommandType.Cat, args)
        assertThrows<FileError> { cmdCat.execute(EnvironmentContextImpl(VariableContextImpl())) }
    }

    /* If the file does not exist execute() must throw FileError */
    @Test
    fun testExecuteWcWrongFile() {
        val path = pathToTestDirectory + "WrongFileName.txt"
        val args = arrayOf(path).map { Arg(it) }
        val cmdWc = CommandFactoryImpl().createCommand(CommandType.Wc, args)
        assertThrows<FileError> { cmdWc.execute(EnvironmentContextImpl(VariableContextImpl())) }
    }

    /* If the file or folder does not exist execute() must throw PathError */
    @Test
    fun testExecuteLsWrongFile() {
        val path = pathToTestDirectory + "ls_test/lol"
        val args = arrayOf(path).map { Arg(it) }
        val cmdLs = CommandFactoryImpl().createCommand(CommandType.Ls, args)
        assertThrows<PathError> { cmdLs.execute(EnvironmentContextImpl(VariableContextImpl())) }
    }

    @Test
    fun testUnknownCommand() {
        val args = listOf("echo", "42").map { Arg(it) }
        val cmdUnk = CommandFactoryImpl().createCommand(CommandType.Unknown, args)
        val expected = Optional.of("42") to 0
        Assertions.assertEquals(expected, cmdUnk.execute(EnvironmentContextImpl(VariableContextImpl())))
    }
}