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

    @Test
    fun testExecuteCDValid() {
        var startedPath = System.getProperty("user.dir")


        val args = listOf("..").map { Arg(it) }
        val cmdCD = CommandFactoryImpl().createCommand(CommandType.CD, args)
        cmdCD.execute(VariableContextImpl())

        val expected = File(startedPath).absolutePath

        Assertions.assertEquals(expected, SystemStateSingletonImpl.instance.getPath())
    }
    @Test
    fun testExecuteCDValidNextDir() {
        var startedPath = System.getProperty("user.dir")


        val args = listOf("build").map { Arg(it) }
        val cmdCD = CommandFactoryImpl().createCommand(CommandType.CD, args)
        cmdCD.execute(VariableContextImpl())


        val expected = File(startedPath).absolutePath + "\\build"

        Assertions.assertEquals(expected, SystemStateSingletonImpl.instance.getPath())
    }

    @Test
    fun testExecuteCDValidNextTwoDirWithCheckCat() {
        val args = listOf("testForTests").map { Arg(it) }
        val cmdCD = CommandFactoryImpl().createCommand(CommandType.CD, args)
        cmdCD.execute(VariableContextImpl())
        val path = "tt.txt"
        val argsTwo =  arrayOf(path).map { Arg(it) }
        val cmdCat = CommandFactoryImpl().createCommand(CommandType.Cat, argsTwo)
        cmdCD.execute(VariableContextImpl())


        val expected = Optional.of("червячок _/‾\\_/‾\\_/‾\\_o") to 0
        val actual = cmdCat.execute(VariableContextImpl())
        Assertions.assertEquals(expected, actual)
    }

}