package com.architecturecourse.commandlineinterpreter.entities.singletons

class SystemStateSingletonImpl : SystemStateSingleton {

    private var path = System.getProperty("user.dir")

    override fun getPath(): String {
        return path
    }

    override fun setPath(path: String) {
        this.path = path
    }

    override fun getFullPath(path: String): String {
        var res = path
        if (!res.startsWith("/")){
            res = this.path + "/" + path
        }
        return res
    }

    companion object {
        val instance = SystemStateSingletonImpl()
    }
}