package com.architecturecourse.commandlineinterpreter

import com.architecturecourse.commandlineinterpreter.entities.commandfactory.CommandFactoryImpl
import com.architecturecourse.commandlineinterpreter.entities.context.VariableContextImpl
import com.architecturecourse.commandlineinterpreter.entities.singletons.SystemStateSingletonImpl
import com.architecturecourse.commandlineinterpreter.entities.utils.Arg
import com.architecturecourse.commandlineinterpreter.entities.utils.CommandType
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.propertyeditors.StringArrayPropertyEditor
import java.io.File
import java.lang.reflect.Array
import java.sql.Time
import java.util.*
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

class LsTests {
    var startState = System.getProperty("user.dir")
    fun prepareState() {
        SystemStateSingletonImpl.instance.setPath(startState)
    }

//  main->testForTests->second->first->first:
//  --ter.txt
//  --test.txt
    @Test
    fun testExecuteLsValidOfLsCommandOnTestDirFirst() {
        prepareState()
        val list = File( "testForTests" + File.separator + "second" + File.separator + "first" + File.separator + "first").absolutePath
        val argsCD = listOf(list).map { Arg(it) }
        val cmdCD = CommandFactoryImpl().createCommand(CommandType.CD, argsCD)
        cmdCD.execute(VariableContextImpl())

        val args = listOf<Arg>()
        val cmdLS = CommandFactoryImpl().createCommand(CommandType.LS, args)
        val actual = cmdLS.execute(VariableContextImpl())

        var startedPath = System.getProperty("user.dir")
        val expected = File(File(startedPath).absolutePath + File.separator + "testForTests" + File.separator +
                "second" + File.separator + "first" + File.separator + "first").absolutePath
        var dirFiles = actual.first.get().split("\n")

        File(expected).listFiles().forEach {Assertions.assertTrue(dirFiles.contains(it.name))}
    }

//  main->testForTests->second->first->first:
//  --first
//  --second
//  --tt.txt
    @Test
    fun testExecuteLsValidOfLsCommandOnTestDirSecond() {
        prepareState()
        val list = File( "testForTests").absolutePath
        val argsCD = listOf(list).map { Arg(it) }
        val cmdCD = CommandFactoryImpl().createCommand(CommandType.CD, argsCD)
        cmdCD.execute(VariableContextImpl())

        val args = listOf<Arg>()
        val cmdLS = CommandFactoryImpl().createCommand(CommandType.LS, args)
        val actual = cmdLS.execute(VariableContextImpl())

        var startedPath = System.getProperty("user.dir")
        val expected = File(File(startedPath).absolutePath + File.separator + "testForTests").absolutePath
        var dirFiles = actual.first.get().split("\n")

        File(expected).listFiles().forEach {Assertions.assertTrue(dirFiles.contains(it.name))}
    }

//  main->testForTests->first->first:
//  --test.txt
    @Test
    fun testExecuteLsValidOfLsCommandOnTestDirThird() {
        prepareState()
        val list = File( "testForTests" + File.separator + "first" + File.separator + "first").absolutePath
        val argsCD = listOf(list).map { Arg(it) }
        val cmdCD = CommandFactoryImpl().createCommand(CommandType.CD, argsCD)
        cmdCD.execute(VariableContextImpl())

        val args = listOf<Arg>()
        val cmdLS = CommandFactoryImpl().createCommand(CommandType.LS, args)
        val actual = cmdLS.execute(VariableContextImpl())

        var startedPath = System.getProperty("user.dir")
        val expected = File(File(startedPath).absolutePath + File.separator + "testForTests" + File.separator +
                "first" + File.separator + "first").absolutePath
        var dirFiles = actual.first.get().split("\n")

        File(expected).listFiles().forEach {Assertions.assertTrue(dirFiles.contains(it.name))}
    }

//  main->testForTests->third->first:
//  --second
//  --gr.py
    @Test
    fun testExecuteLsValidOfLsCommandOnTestDirFourth() {
        prepareState()
        val list = File( "testForTests" + File.separator + "third" + File.separator + "first").absolutePath
        val argsCD = listOf(list).map { Arg(it) }
        val cmdCD = CommandFactoryImpl().createCommand(CommandType.CD, argsCD)
        cmdCD.execute(VariableContextImpl())

        val args = listOf<Arg>()
        val cmdLS = CommandFactoryImpl().createCommand(CommandType.LS, args)
        val actual = cmdLS.execute(VariableContextImpl())

        var startedPath = System.getProperty("user.dir")
        val expected = File(File(startedPath).absolutePath + File.separator + "testForTests" + File.separator +
                "third" + File.separator + "first").absolutePath
        var dirFiles = actual.first.get().split("\n")

        File(expected).listFiles().forEach {Assertions.assertTrue(dirFiles.contains(it.name))}
    }

//  main->testForTests->third->first:
//  --second
//  --gr.py
    @Test
    fun testExecuteLsValidOfLsCommandOnTestDirFifth() {
        prepareState()
        val list = File( "testForTests" + File.separator + "third" + File.separator + "first").absolutePath
        val argsCD = listOf(list).map { Arg(it) }
        val cmdCD = CommandFactoryImpl().createCommand(CommandType.CD, argsCD)
        cmdCD.execute(VariableContextImpl())

        val args = listOf<Arg>()
        val cmdLS = CommandFactoryImpl().createCommand(CommandType.LS, args)
        val actual = cmdLS.execute(VariableContextImpl())

        var startedPath = System.getProperty("user.dir")
        val expected = File(File(startedPath).absolutePath + File.separator + "testForTests" + File.separator +
                "third" + File.separator + "first").absolutePath
        var dirFiles = actual.first.get().split("\n")

        File(expected).listFiles().forEach {Assertions.assertTrue(dirFiles.contains(it.name))}
    }

//  main->testForTests->third->first->second:
//  --ff.txt
//  --gt.py
//  --jj.txt
//  --rr.txt
    @Test
    fun testExecuteLsValidOfLsCommandOnTestDirSixth() {
        prepareState()
        val list = File( "testForTests" + File.separator + "third" + File.separator + "first" + File.separator
                + "second").absolutePath
        val argsCD = listOf(list).map { Arg(it) }
        val cmdCD = CommandFactoryImpl().createCommand(CommandType.CD, argsCD)
        cmdCD.execute(VariableContextImpl())

        val args = listOf<Arg>()
        val cmdLS = CommandFactoryImpl().createCommand(CommandType.LS, args)
        val actual = cmdLS.execute(VariableContextImpl())

        var startedPath = System.getProperty("user.dir")
        val expected = File(File(startedPath).absolutePath + File.separator + "testForTests" + File.separator +
                "third" + File.separator + "first" + File.separator + "second").absolutePath
        var dirFiles = actual.first.get().split("\n")

        File(expected).listFiles().forEach {Assertions.assertTrue(dirFiles.contains(it.name))}
    }

//  main->testForTests->empty:
    @Test
    fun testExecuteLsValidOfLsCommandOnTestDirSeventh() {
        prepareState()
        val list = File( "testForTests" + File.separator + "empty").absolutePath
        val argsCD = listOf(list).map { Arg(it) }
        val cmdCD = CommandFactoryImpl().createCommand(CommandType.CD, argsCD)
        cmdCD.execute(VariableContextImpl())

        val args = listOf<Arg>()
        val cmdLS = CommandFactoryImpl().createCommand(CommandType.LS, args)
        val actual = cmdLS.execute(VariableContextImpl())

        val expected = Optional.of("") to 0

        Assertions.assertEquals(expected, actual)
    }

}