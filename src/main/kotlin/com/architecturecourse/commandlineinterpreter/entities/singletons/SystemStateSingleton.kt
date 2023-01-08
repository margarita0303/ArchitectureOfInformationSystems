package com.architecturecourse.commandlineinterpreter.entities.singletons

interface SystemStateSingleton {

    fun getPath() : String
    fun setPath(path : String)

    fun getFullPath(path : String) : String

}