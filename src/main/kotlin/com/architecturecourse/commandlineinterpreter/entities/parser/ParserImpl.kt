package com.architecturecourse.commandlineinterpreter.entities.parser

import com.architecturecourse.commandlineinterpreter.entities.utils.Arg
import com.architecturecourse.commandlineinterpreter.entities.utils.CommandData
import com.architecturecourse.commandlineinterpreter.entities.utils.CommandType
import com.architecturecourse.commandlineinterpreter.entities.utils.Token
import com.architecturecourse.commandlineinterpreter.entities.utils.error.EmptyTokenListError


class ParserImpl : Parser {
    override fun parse(tokens: List<List<Token>>): List<CommandData> {
        if (tokens.isEmpty())
            throw EmptyTokenListError

        return tokens.map { section ->
            val args = section.map { Arg(it.data) }.drop(1)
            when (section.first().data) {
                CommandType.Echo.TAG -> CommandData(CommandType.Echo, args)
                CommandType.Cat.TAG -> CommandData(CommandType.Cat, args)
                CommandType.Pwd.TAG -> CommandData(CommandType.Pwd, args)
                CommandType.Wc.TAG -> CommandData(CommandType.Wc, args)
                CommandType.Exit.TAG -> CommandData(CommandType.Exit, args)
                CommandType.Assign.TAG -> CommandData(CommandType.Assign, args)
                CommandType.Grep.TAG -> CommandData(CommandType.Grep, args)
                CommandType.Cd.TAG -> CommandData(CommandType.Cd, args)
                else -> CommandData(CommandType.Unknown, listOf(Arg(section.first().data)) + args)
            }
        }
    }
}
