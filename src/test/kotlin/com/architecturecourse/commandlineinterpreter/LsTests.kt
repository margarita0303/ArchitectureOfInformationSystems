package com.architecturecourse.commandlineinterpreter

import com.architecturecourse.commandlineinterpreter.entities.commandfactory.CommandFactoryImpl
import com.architecturecourse.commandlineinterpreter.entities.context.VariableContextImpl
import com.architecturecourse.commandlineinterpreter.entities.utils.Arg
import com.architecturecourse.commandlineinterpreter.entities.utils.CommandType
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File

class LsTests {

//    TODO: дирректорию с папками для провреки уровней перехода. Тесты на переход сделать. С указанием конкретного файла
//
    @Test
    fun testExecuteLsValidSimpleVariantOfLsCommand() {
        val args = listOf<Arg>()
        val cmdLS = CommandFactoryImpl().createCommand(CommandType.LS, args)
        val actual = cmdLS.execute(VariableContextImpl())
        var startedPath = System.getProperty("user.dir")
        var dirFiles = actual.first.get().split("\n")

        File(startedPath).listFiles().forEach {Assertions.assertTrue(dirFiles.contains(it.name))}
    }

    @Test
    fun testExecuteLsValidSimpleVariantOfLsCommandOneTestDir() {
        val args = listOf<Arg>()
        val cmdLS = CommandFactoryImpl().createCommand(CommandType.LS, args)
        val actual = cmdLS.execute(VariableContextImpl())
        var startedPath = System.getProperty("user.dir")
        var dirFiles = actual.first.get().split("\n")

        File(startedPath).listFiles().forEach {Assertions.assertTrue(dirFiles.contains(it.name))}
    }
}