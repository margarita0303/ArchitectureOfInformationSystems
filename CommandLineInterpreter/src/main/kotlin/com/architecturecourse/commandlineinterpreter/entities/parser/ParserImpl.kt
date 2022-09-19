package com.architecturecourse.commandlineinterpreter.entities.parser

import com.architecturecourse.commandlineinterpreter.entities.utils.Arg
import com.architecturecourse.commandlineinterpreter.entities.utils.CmdCategory
import com.architecturecourse.commandlineinterpreter.entities.utils.CmdType
import com.architecturecourse.commandlineinterpreter.entities.utils.Token
import com.architecturecourse.commandlineinterpreter.entities.utils.error.EmptyTokenListError


class ParserImpl : Parser {
    override fun parse(tokensCat: Pair<List<Token>, CmdCategory>): Pair<CmdType, List<Arg>> {
        val (tokens, cat) = tokensCat
        if (tokens.isEmpty())
            throw EmptyTokenListError

        val args = tokens.map { Arg(it.s) }
        return when (cat) {
            CmdCategory.Assign -> CmdType.Assign to args
            CmdCategory.Invoke -> when (tokens.first().s) {
                CmdType.Echo.TAG -> CmdType.Echo to args.drop(1)
                CmdType.Cat.TAG -> CmdType.Cat to args.drop(1)
                CmdType.Pwd.TAG -> CmdType.Pwd to args.drop(1)
                CmdType.Wc.TAG -> CmdType.Wc to args.drop(1)
                CmdType.Exit.TAG -> CmdType.Exit to args.drop(1)
                else -> CmdType.Unknown to args.drop(1)
            }
        }
    }
}