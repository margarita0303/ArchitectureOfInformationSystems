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
            var path = args[0]

            if (isAbsolutePath(path)) {
                // absolute path
                if (path.startsWith("/")) {
                    path = SystemStateSingletonImpl.instance.getFullPath(path)
                }
                if (File(path).exists()) {
                    SystemStateSingletonImpl.instance.setPath(path)
                }
            } else {
                var curFile = File(SystemStateSingletonImpl.instance.getPath())
                var dirList = path.split("/")

                dirList.forEach {
                    if (it.equals("..")) {
                        if (curFile.parentFile != null) {
                            curFile = curFile.parentFile
                        }
                    } else {
                        var nextFile = File(curFile.absolutePath + "/" + it)
                        if (nextFile.exists()) {
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

    private fun isAbsolutePath(path: String): Boolean {
        if (path.startsWith("/")) {
            return true
        }

        var isWindows = System.getProperty("os.name")
            .lowercase(Locale.getDefault()).startsWith("windows")

        if (isWindows) {
            var dir = path.split("/")
            if (!dir.get(0).equals("..") && File(dir.get(0)).exists()) {
                return true
            }
        }
        return false
    }

    // червячок _/‾\_/‾\_/‾\_o

    init {
        if (args.size > expectedNumberOfArgs)
            throw WrongNumberOfArgumentsError
    }

}