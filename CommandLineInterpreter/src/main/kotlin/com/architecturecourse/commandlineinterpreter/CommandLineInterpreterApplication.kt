package com.architecturecourse.commandlineinterpreter

import com.architecturecourse.commandlineinterpreter.components.interpretersession.InterpreterSession
import com.architecturecourse.commandlineinterpreter.components.interpretersession.InterpreterSessionImpl
import com.architecturecourse.commandlineinterpreter.components.userinterface.UserInterface
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean


@SpringBootApplication
class CommandLineInterpreterApplication {

    @Bean
    fun interpreterSession(userInterface: UserInterface): InterpreterSession {
        return InterpreterSessionImpl(userInterface)
    }

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


