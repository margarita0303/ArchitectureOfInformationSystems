package com.architecturecourse.commandlineinterpreter.entities.command

import com.architecturecourse.commandlineinterpreter.entities.context.VariableContext
import com.architecturecourse.commandlineinterpreter.entities.utils.error.WrongNumberOfArgumentsError
import java.util.*

class AssignCommand(private val args: List<String>): Command {
    override val expectedNumberOfArgs: Int = 2
    init {
        if(args.size != expectedNumberOfArgs)
            throw WrongNumberOfArgumentsError
    }

    override fun execute(context: VariableContext): Pair<Optional<String>, Int> {
        context.addOrUpdateVar(args[0], args[1])
        return Optional.empty<String>() to 0
    }
}