package com.architecturecourse.commandlineinterpreter

import com.architecturecourse.commandlineinterpreter.entities.commandfactory.CommandFactoryImpl
import com.architecturecourse.commandlineinterpreter.entities.context.VariableContextImpl
import com.architecturecourse.commandlineinterpreter.entities.singletons.SystemStateSingletonImpl
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
        // Берем старотовый путь
        var startedPath = System.getProperty("user.dir")

        // Делаем команду выхода назад от текущей дириктории
        val args = listOf("..").map { Arg(it) }
        val cmdCD = CommandFactoryImpl().createCommand(CommandType.CD, args)
        cmdCD.execute(VariableContextImpl())

        // Вручнуб берем родительский файл от стартовой дириктории
        val expected = File(startedPath).parentFile.absolutePath

        // Сравниваем с текущей дириктории. Она хранится в SystemStateSingletonImpl.instance.getPath()
        Assertions.assertEquals(expected, SystemStateSingletonImpl.instance.getPath())
    }
}

