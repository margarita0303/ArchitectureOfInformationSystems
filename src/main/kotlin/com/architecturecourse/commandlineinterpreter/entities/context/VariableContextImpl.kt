package com.architecturecourse.commandlineinterpreter.entities.context

class VariableContextImpl : VariableContext {
    private val data = System.getenv().toMutableMap()

    override fun getVar(name: String): String? {
        return data[name]
    }

    override fun addOrUpdateVar(name: String, value: String) {
        data[name] = value
    }
}