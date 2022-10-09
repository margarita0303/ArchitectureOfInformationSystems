package com.architecturecourse.commandlineinterpreter.configurations
import picocli.CommandLine.Option
import picocli.CommandLine.Parameters

class GrepConfiguration {
    @Option(names = ["-w"], description = ["Search only the whole word"])
    var fullWord = false

    @Option(names = ["-i"], description = ["Case-insensitive search"])
    var caseInsensitive = false

    @Option(names = ["-A"], description = ["How many lines after the match should be printed"])
    var lines: Int? = null

    @Parameters(paramLabel = "WORD", description = ["Word of string to found"])
    lateinit var word: String

    @Parameters(paramLabel = "FILE", description = ["Search file"])
    lateinit var file: String
}