package com.architecturecourse.commandlineinterpreter.entities.parser

import com.architecturecourse.commandlineinterpreter.entities.utils.Arg
import com.architecturecourse.commandlineinterpreter.entities.utils.CmdType
import com.architecturecourse.commandlineinterpreter.entities.utils.Token
import com.architecturecourse.commandlineinterpreter.entities.utils.error.EmptyTokenListError


class ParserImpl : Parser {
    override fun parse(tokens: List<Token>): Pair<CmdType, List<Arg>> {
        if (tokens.isEmpty())
            throw EmptyTokenListError

        val args = tokens.map { Arg(it.s) }.drop(1)
        return when (tokens.first().s) {
            CmdType.Echo.TAG -> CmdType.Echo to args
            CmdType.Cat.TAG -> CmdType.Cat to args
            CmdType.Pwd.TAG -> CmdType.Pwd to args
            CmdType.Wc.TAG -> CmdType.Wc to args
            CmdType.Exit.TAG -> CmdType.Exit to args
            CmdType.Assign.TAG -> CmdType.Assign to args
            else -> CmdType.Unknown to args
        }
    }
}
