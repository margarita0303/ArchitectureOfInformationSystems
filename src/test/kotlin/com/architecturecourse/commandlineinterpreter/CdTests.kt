package com.architecturecourse.commandlineinterpreter

import com.architecturecourse.commandlineinterpreter.entities.commandfactory.CommandFactoryImpl
import com.architecturecourse.commandlineinterpreter.entities.context.VariableContextImpl
import com.architecturecourse.commandlineinterpreter.entities.utils.Arg
import com.architecturecourse.commandlineinterpreter.entities.utils.CommandType
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File
import java.util.*

class CdTests {

    private val isWindows = System.getProperty("os.name")
        .lowercase(Locale.getDefault()).startsWith("windows")

    var pathToTestDirectory = "src/test/kotlin/com/architecturecourse/commandlineinterpreter/"

    @Test
    fun testExecuteCDValid() {
            val args = listOf("echo", "42").map { Arg(it) }
            val cmdUnk = CommandFactoryImpl().createCommand(CommandType.Unknown, args)
            val expected = Optional.of("42") to 0
            Assertions.assertEquals(expected, cmdUnk.execute(VariableContextImpl()))
}
}

