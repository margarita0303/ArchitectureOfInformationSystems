package com.architecturecourse.commandlineinterpreter.entities.command.utils
import picocli.CommandLine.Option
import picocli.CommandLine.Parameters

class GrepArgsConfiguration {
    @Option(names = ["-w"], description = ["Search only the whole word"])
    var fullWord = false

    @Option(names = ["-i"], description = ["Case-insensitive search"])
    var caseInsensitive = false

    @Option(names = ["-A"], description = ["How many lines after the match should be printed"])
    var lines: Int? = null

    @Parameters(paramLabel = "WORD", description = ["Word of string to found"], arity = "1", index = "0")
    lateinit var word: String

    @Parameters(paramLabel = "FILE", description = ["Search file"], arity = "0..1", index = "1")
    var file: String? = null
}