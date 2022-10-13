package com.architecturecourse.commandlineinterpreter.entities.singletons

class SystemStateSingletonImpl : SystemStateSingleton {

    private var path = System.getProperty("user.dir")

    override fun getPath(): String? {
        return path
    }

    override fun setPath(path: String) {
        this.path = path
    }

    companion object {
        val instance = SystemStateSingletonImpl()
    }
}