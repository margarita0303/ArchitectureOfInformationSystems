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


    //    main->TestForTests
    @Test
    fun testExecuteCDValidNextTestDir() {
        var startedPath = System.getProperty("user.dir")

        val args = listOf("testForTests").map { Arg(it) }
        val cmdCD = CommandFactoryImpl().createCommand(CommandType.CD, args)
        cmdCD.execute(VariableContextImpl())

        val expected = File(File(startedPath).absolutePath + File.separator + "testForTests").absolutePath

        Assertions.assertEquals(expected, SystemStateSingletonImpl.instance.getPath())
    }

    //    main->notValidDir
    @Test
    fun testExecuteCDNotValidNextTestDir() {
        var startedPath = System.getProperty("user.dir")

        val args = listOf("notValid").map { Arg(it) }
        val cmdCD = CommandFactoryImpl().createCommand(CommandType.CD, args)
        cmdCD.execute(VariableContextImpl())

//        val expected = File(startedPath).absolutePath
        val expected = File(File(startedPath).absolutePath.split(File.separator)[0] + File.separator).absolutePath

        Assertions.assertEquals(expected, SystemStateSingletonImpl.instance.getPath())
    }

    //    main->testForTests->main
    @Test
    fun testExecuteCDValidNextTestDirAndReturnBack() {
        var startedPath = System.getProperty("user.dir")

        val args = listOf("testForTests").map { Arg(it) }
        val cmdCD = CommandFactoryImpl().createCommand(CommandType.CD, args)
        cmdCD.execute(VariableContextImpl())

        val argsSecond = listOf("..").map { Arg(it) }
        val cmdCDSecond = CommandFactoryImpl().createCommand(CommandType.CD, argsSecond)
        cmdCDSecond.execute(VariableContextImpl())

        val expected = File(startedPath).absolutePath

        Assertions.assertEquals(expected, SystemStateSingletonImpl.instance.getPath())
    }

    //  main->testForTests->second->first->first
    @Test
    fun testExecuteCDValidNextTestDirAbsolutePath() {
        var startedPath = System.getProperty("user.dir")

        val list = File( "testForTests" + File.separator + "second" + File.separator + "first" + File.separator + "first").absolutePath
        val args = listOf(list).map { Arg(it) }
        val cmdCD = CommandFactoryImpl().createCommand(CommandType.CD, args)
        cmdCD.execute(VariableContextImpl())

        val expected = File(File(startedPath).absolutePath + File.separator + "testForTests" + File.separator +
                "second" + File.separator + "first" + File.separator + "first").absolutePath

        Assertions.assertEquals(expected, SystemStateSingletonImpl.instance.getPath())
    }

    //  main->testForTests->second->first->first->main
    @Test
    fun testExecuteCDValidAFewDots() {
        var startedPath = System.getProperty("user.dir")

        val list = File( "testForTests" + File.separator + "second" + File.separator + "first" + File.separator + "first").absolutePath
        val args = listOf(list).map { Arg(it) }
        val cmdCD = CommandFactoryImpl().createCommand(CommandType.CD, args)
        cmdCD.execute(VariableContextImpl())

        val argsSecond = listOf("../../../..").map { Arg(it) }
        val cmdCDSecond = CommandFactoryImpl().createCommand(CommandType.CD, argsSecond)
        cmdCDSecond.execute(VariableContextImpl())

        val expected = File(startedPath).absolutePath

        Assertions.assertEquals(expected, SystemStateSingletonImpl.instance.getPath())
    }

    //  main->globalParent just cd, for example to C:
    @Test
    fun testExecuteCDValidToGlobalParent() {
        var startedPath = System.getProperty("user.dir")

        val list = File(startedPath).absolutePath.split(File.separator)[0]
        val args = listOf(list).map { Arg(it) }
        val cmdCD = CommandFactoryImpl().createCommand(CommandType.CD, args)
        cmdCD.execute(VariableContextImpl())

        val expected = File(File(startedPath).absolutePath.split(File.separator)[0] + File.separator).absolutePath

        Assertions.assertEquals(expected, SystemStateSingletonImpl.instance.getPath())
    }


    //  main->globalParent with "cd.." upper of global parent
    @Test
    fun testExecuteCDCheckUpperOfGlobalParent() {
        var startedPath = System.getProperty("user.dir")

        val list = File(startedPath).absolutePath.split(File.separator)[0]
        val args = listOf(list).map { Arg(it) }
        val cmdCD = CommandFactoryImpl().createCommand(CommandType.CD, args)
        cmdCD.execute(VariableContextImpl())

        val argsSecond = listOf("..").map { Arg(it) }
        val cmdCDSecond = CommandFactoryImpl().createCommand(CommandType.CD, argsSecond)
        val actual = cmdCDSecond.execute(VariableContextImpl())

        val expected = Optional.of("Something went wrong: curFile.parentFile must not be null)\n" +
                "Process finished with exit code 128.") to 0

        Assertions.assertEquals(expected, actual)
    }

    //  main->testForTests->read tt.txt
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