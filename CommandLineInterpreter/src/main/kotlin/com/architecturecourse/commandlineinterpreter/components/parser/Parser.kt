package com.architecturecourse.commandlineinterpreter.components.parser

import com.architecturecourse.commandlineinterpreter.components.utils.Arg
import com.architecturecourse.commandlineinterpreter.components.utils.CmdCategory
import com.architecturecourse.commandlineinterpreter.components.utils.CmdType
import com.architecturecourse.commandlineinterpreter.components.utils.Token

interface Parser {
    fun parse(tokensCat: Pair<List<Token>, CmdCategory>): Pair<CmdType, List<Arg>>
}