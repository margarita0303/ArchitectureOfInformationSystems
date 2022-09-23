package com.architecturecourse.commandlineinterpreter.entities.cmd

import com.architecturecourse.commandlineinterpreter.entities.context.VariableContext
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.util.*
import java.util.concurrent.Executors

/* If something is entered that the interpreter does not know, call an external program */
class UnknownCmd(override val args: List<String>) : Cmd {
    override val numberOfArgs: Int? = null
    override fun execute(context: VariableContext): Pair<String, Int> {
        val isWindows = System.getProperty("os.name")
            .lowercase(Locale.getDefault()).startsWith("windows")

        val process: Process = if (isWindows) {
            Runtime.getRuntime()
                .exec("cmd.exe /c ${args.joinToString(separator = " ")}")
        } else {
            Runtime.getRuntime()
                .exec(java.lang.String.format("sh -c ${args.joinToString(separator = " ")}"))
        }

        val output = mutableListOf<String?>()
        val streamGobbler = StreamGobbler(process.inputStream, output)
        val future = Executors.newSingleThreadExecutor().submit(streamGobbler)

        val exitCode = process.waitFor()
        assert(exitCode == 0)

        future.get()
        return output.joinToString("\n") to 0
    }

    private class StreamGobbler(private val inputStream: InputStream, consumer: MutableList<String?>) :
        Runnable {
        private val consumer: MutableList<String?>

        init {
            this.consumer = consumer
        }

        override fun run() {
            BufferedReader(InputStreamReader(inputStream)).lines().forEach { consumer.add(it) }
        }
    }
}