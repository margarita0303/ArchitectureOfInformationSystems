package com.architecturecourse.commandlineinterpreter

import com.architecturecourse.commandlineinterpreter.configurations.GrepConfiguration
import org.junit.jupiter.api.Test
import picocli.CommandLine

class GrepConfigurationTests {
    @Test
    fun testAllKeys() {
        val args = arrayOf("-i", "-w", "-A", "1", "минимальный", "file.txt")
        val grepConfiguration = GrepConfiguration()
        CommandLine(grepConfiguration).parseArgs(*args)

        assert(grepConfiguration.fullWord)
        assert(grepConfiguration.caseInsensitive)
        grepConfiguration.lines?.let { assert(it == 1) }
        assert(grepConfiguration.file == "file.txt")
        assert(grepConfiguration.word == "минимальный")
    }

    @Test
    fun testWithPartOfParameters() {
        val args = arrayOf("-i", "-A", "10", "минимальный", "file.txt")
        val grepConfiguration = GrepConfiguration()
        CommandLine(grepConfiguration).parseArgs(*args)

        assert(!grepConfiguration.fullWord)
        assert(grepConfiguration.caseInsensitive)
        grepConfiguration.lines?.let { assert(it == 10) }
        assert(grepConfiguration.file == "file.txt")
        assert(grepConfiguration.word == "минимальный")
    }
}