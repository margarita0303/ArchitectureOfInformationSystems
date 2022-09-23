package com.architecturecourse.commandlineinterpreter.entities.context

interface VariableContext {
    fun getVar(name: String) : String?
    fun addOrUpdateVar(name: String, value: String)
}