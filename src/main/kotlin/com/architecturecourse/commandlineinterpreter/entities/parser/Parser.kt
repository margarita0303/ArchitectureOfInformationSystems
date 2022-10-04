package com.architecturecourse.commandlineinterpreter.entities.parser

import com.architecturecourse.commandlineinterpreter.entities.utils.CommandData
import com.architecturecourse.commandlineinterpreter.entities.utils.Token

/* Parse token list to the object which denotes command type and arguments list */
interface Parser {
    fun parse(tokens: List<List<Token>>): List<CommandData>
}