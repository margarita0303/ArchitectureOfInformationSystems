package com.architecturecourse.commandlineinterpreter

import com.architecturecourse.commandlineinterpreter.entities.commandfactory.CommandFactoryImpl
import com.architecturecourse.commandlineinterpreter.entities.context.VariableContextImpl
import com.architecturecourse.commandlineinterpreter.entities.utils.Arg
import com.architecturecourse.commandlineinterpreter.entities.utils.CommandType
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File
import java.util.*

class LsTests {

    private val isWindows = System.getProperty("os.name")
        .lowercase(Locale.getDefault()).startsWith("windows")

    var pathToTestDirectory = "src/test/kotlin/com/architecturecourse/commandlineinterpreter/"

    @Test
    fun testExecuteLsValid() {
        val args = listOf<Arg>()
        println(args)
        val cmdLS = CommandFactoryImpl().createCommand(CommandType.LS, args)
        val expected = Optional.of(".git\n"+".github\n" +
                ".gradle\n" +
                ".idea\n" +
                "build\n" +
                "build.gradle.kts\n" +
                "DesigningASimpleCommandLineInterpreter\n" +
                "feedback.md\n" +
                "gradle\n" +
                "gradlew\n" +
                "gradlew.bat\n" +
                "README.md\n" +
                "settings.gradle.kts\n" +
                "src\n") to 0
        val actual = cmdLS.execute(VariableContextImpl())
        Assertions.assertEquals(expected, actual)
    }

}