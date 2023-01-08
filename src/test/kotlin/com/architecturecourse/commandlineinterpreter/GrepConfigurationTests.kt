package com.architecturecourse.commandlineinterpreter

import com.architecturecourse.commandlineinterpreter.entities.command.utils.GrepArgsConfiguration
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import picocli.CommandLine

class GrepConfigurationTests {
    @Test
    fun testAllKeys() {
        val args = arrayOf("-i", "-w", "-A", "1", "минимальный", "file.txt")
        val grepConfiguration = GrepArgsConfiguration()
        CommandLine(grepConfiguration).parseArgs(*args)

        assert(grepConfiguration.fullWord)
        assert(grepConfiguration.caseInsensitive)
        grepConfiguration.lines?.let { assert(it == 1) }
        assertEquals(grepConfiguration.file,  "file.txt")
        assertEquals(grepConfiguration.word,"минимальный")
    }

    @Test
    fun testWithPartOfParameters() {
        val args = arrayOf("-i", "-A", "10", "минимальный", "file.txt")
        val grepConfiguration = GrepArgsConfiguration()
        CommandLine(grepConfiguration).parseArgs(*args)

        assert(!grepConfiguration.fullWord)
        assert(grepConfiguration.caseInsensitive)
        grepConfiguration.lines?.let { assert(it == 10) }
        assertEquals(grepConfiguration.file,  "file.txt")
        assertEquals(grepConfiguration.word,"минимальный")
    }

    @Test
    fun testWithOutRequiredParameters() {
        val args = arrayOf("-i", "-A", "10", "минимальный")
        val grepConfiguration = GrepArgsConfiguration()
        CommandLine(grepConfiguration).parseArgs(*args)

        assert(!grepConfiguration.fullWord)
        assert(grepConfiguration.caseInsensitive)
        grepConfiguration.lines?.let { assert(it == 10) }
        assertEquals(grepConfiguration.file, null)
        assertEquals(grepConfiguration.word, "минимальный")
    }
}