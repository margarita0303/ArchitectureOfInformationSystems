package com.architecturecourse.commandlineinterpreter.entities.parser

import com.architecturecourse.commandlineinterpreter.entities.utils.Arg
import com.architecturecourse.commandlineinterpreter.entities.utils.CommandData
import com.architecturecourse.commandlineinterpreter.entities.utils.CommandType
import com.architecturecourse.commandlineinterpreter.entities.utils.Token
import com.architecturecourse.commandlineinterpreter.entities.utils.error.EmptyTokenListError


class ParserImpl : Parser {
    override fun parse(tokens: List<Token>): CommandData {
        if (tokens.isEmpty())
            throw EmptyTokenListError

        val args = tokens.map { Arg(it.data) }.drop(1)
        return when (tokens.first().data) {
            CommandType.Echo.TAG -> CommandData(CommandType.Echo, args)
            CommandType.Cat.TAG -> CommandData(CommandType.Cat, args)
            CommandType.Pwd.TAG -> CommandData(CommandType.Pwd, args)
            CommandType.Wc.TAG -> CommandData(CommandType.Wc, args)
            CommandType.Exit.TAG -> CommandData(CommandType.Exit, args)
            CommandType.Assign.TAG -> CommandData(CommandType.Assign, args)
            else -> CommandData(CommandType.Unknown, listOf(Arg(tokens.first().data)) + args)
        }
    }
}
