package com.architecturecourse.commandlineinterpreter.entities.userinterface

class UserInterfaceImpl : UserInterface {
    override fun input(): String {
        print("> ")
        return readln()
    }

    override fun output(string: String) {
        println(string)
    }
}