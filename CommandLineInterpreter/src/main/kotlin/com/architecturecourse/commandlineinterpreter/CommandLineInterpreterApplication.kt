package com.architecturecourse.commandlineinterpreter

import com.architecturecourse.commandlineinterpreter.entities.interpretersession.InterpreterSession
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean


@SpringBootApplication
class CommandLineInterpreterApplication {
    @Bean
    fun commandLineRunnerBean(interpreterSession: InterpreterSession): CommandLineRunner? {
        return CommandLineRunner {
            interpreterSession.launch()
        }
    }
}

fun main(args: Array<String>) {
    runApplication<CommandLineInterpreterApplication>(*args)
}


