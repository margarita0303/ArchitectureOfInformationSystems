package com.architecturecourse.commandlineinterpreter.entities.parser

import com.architecturecourse.commandlineinterpreter.entities.utils.Arg
import com.architecturecourse.commandlineinterpreter.entities.utils.CmdType
import com.architecturecourse.commandlineinterpreter.entities.utils.Token

interface Parser {
    fun parse(tokens: List<Token>): Pair<CmdType, List<Arg>>
}