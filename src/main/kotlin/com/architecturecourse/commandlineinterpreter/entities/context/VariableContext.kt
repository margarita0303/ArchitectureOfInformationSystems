package com.architecturecourse.commandlineinterpreter.entities.context

/* Stores variables key-value */
interface VariableContext {
    fun getVar(name: String) : String?
    fun addOrUpdateVar(name: String, value: String)
}