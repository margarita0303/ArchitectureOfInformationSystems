package com.architecturecourse.commandlineinterpreter.entities.parser

import com.architecturecourse.commandlineinterpreter.entities.utils.Arg
import com.architecturecourse.commandlineinterpreter.entities.utils.CmdCategory
import com.architecturecourse.commandlineinterpreter.entities.utils.CmdType
import com.architecturecourse.commandlineinterpreter.entities.utils.Token

interface Parser {
    fun parse(tokensCat: Pair<List<Token>, CmdCategory>): Pair<CmdType, List<Arg>>
}