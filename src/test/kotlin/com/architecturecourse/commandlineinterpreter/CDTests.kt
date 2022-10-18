package com.architecturecourse.commandlineinterpreter

import com.architecturecourse.commandlineinterpreter.entities.commandfactory.CommandFactoryImpl
import com.architecturecourse.commandlineinterpreter.entities.context.VariableContextImpl
import com.architecturecourse.commandlineinterpreter.entities.utils.Arg
import com.architecturecourse.commandlineinterpreter.entities.utils.CommandType
import com.architecturecourse.commandlineinterpreter.entities.utils.exit.ExitInterruption
import com.architecturecourse.commandlineinterpreter.entities.utils.error.FileError
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.io.File
import java.util.*


class CDTests {
    private val isWindows = System.getProperty("os.name")
        .lowercase(Locale.getDefault()).startsWith("windows")

    var pathToTestDirectory = "src/test/kotlin/com/architecturecourse/commandlineinterpreter/"

    @Test
    fun testExecuteCDValid() {
        val path = File("").absolutePath
        val args = arrayOf(path).map { Arg("") }
        println(args)
        val cmdCD = CommandFactoryImpl().createCommand(CommandType.CD, args)
        val expected = Optional.of(File("").absolutePath + "/") to 0
        println(expected)
        val actual = cmdCD.execute(VariableContextImpl())
        Assertions.assertEquals(expected, actual)
    }

}