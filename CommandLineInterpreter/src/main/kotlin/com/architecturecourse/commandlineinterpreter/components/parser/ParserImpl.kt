package com.architecturecourse.commandlineinterpreter.components.parser

import com.architecturecourse.commandlineinterpreter.components.utils.Arg
import com.architecturecourse.commandlineinterpreter.components.utils.CmdCategory
import com.architecturecourse.commandlineinterpreter.components.utils.CmdType
import com.architecturecourse.commandlineinterpreter.components.utils.Token


class ParserImpl : Parser {
    override fun parse(tokensCat: Pair<List<Token>, CmdCategory>): Pair<CmdType, List<Arg>> {
        val (tokens, cat) = tokensCat
        if(tokens.isEmpty())
            throw Error("Empty tokens list.")

        val args = tokens.map { Arg(it.s) }
        return when (cat) {
            CmdCategory.Assign -> CmdType.Assign to args
            CmdCategory.Invoke -> when(tokens.first().s) {
                CmdType.Echo.TAG -> CmdType.Echo to args.drop(1)
                CmdType.Cat.TAG -> CmdType.Cat to args.drop(1)
                CmdType.Pwd.TAG -> CmdType.Pwd to args.drop(1)
                CmdType.Wc.TAG -> CmdType.Wc to args.drop(1)
                else -> CmdType.Unknown to args.drop(1)
            }
        }
    }
}