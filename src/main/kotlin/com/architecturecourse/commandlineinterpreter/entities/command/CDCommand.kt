package com.architecturecourse.commandlineinterpreter.entities.command

import com.architecturecourse.commandlineinterpreter.entities.context.VariableContext
import com.architecturecourse.commandlineinterpreter.entities.singletons.SystemStateSingletonImpl
import com.architecturecourse.commandlineinterpreter.entities.utils.error.WrongNumberOfArgumentsError
import java.io.File
import java.util.*

class CDCommand(private val args: List<String>) : Command {

    override val expectedNumberOfArgs: Int = 1

    override fun execute(context: VariableContext): Pair<Optional<String>, Int> {

        if (args.size == expectedNumberOfArgs) {
            var path = SystemStateSingletonImpl.instance.getFullPath(args[0])

            if (path.startsWith("/")) {
                // absolute path
                if (File(path).exists()) {
                    SystemStateSingletonImpl.instance.setPath(path)
                }
            } else {
                var curFile = File(SystemStateSingletonImpl.instance.getPath())
                var dirList = path.split("/")

                dirList.forEach {
                    if (it.equals("..")) {
                        curFile = curFile.parentFile
                    } else {
                        var nextFile = File(curFile.absolutePath + "/" + it)
                        if(nextFile.exists()){
                            curFile = nextFile
                        } else {
                            return Optional.of("no such file or directory: " + path) to 0
                        }
                    }
                }

                SystemStateSingletonImpl.instance.setPath(curFile.absolutePath)
            }
        }



        return Optional.empty<String>() to 0
    }

    // червячок _/‾\_/‾\_/‾\_o

    init {
        if (args.size > expectedNumberOfArgs)
            throw WrongNumberOfArgumentsError
    }

}