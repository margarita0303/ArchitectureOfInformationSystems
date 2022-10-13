package com.architecturecourse.commandlineinterpreter.entities.command

import com.architecturecourse.commandlineinterpreter.entities.context.VariableContext
import com.architecturecourse.commandlineinterpreter.entities.singletons.SystemStateSingletonImpl
import com.architecturecourse.commandlineinterpreter.entities.utils.error.WrongNumberOfArgumentsError
import java.io.File
import java.util.*

class LSCommand(args: List<String>) : Command {

    override val expectedNumberOfArgs: Int = 0

    override fun execute(context: VariableContext): Pair<Optional<String>, Int> {
        var path = SystemStateSingletonImpl.instance.getPath()
        var res = ""
        File(path).listFiles().forEach { res += it.name + "\n" }

        return Optional.of(res) to 0
    }

    init {
        if(args.size > expectedNumberOfArgs)
            throw WrongNumberOfArgumentsError
    }
}