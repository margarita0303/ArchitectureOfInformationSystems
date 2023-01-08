package com.architecturecourse.commandlineinterpreter.entities.singletons

import java.io.File
import java.util.*

class SystemStateSingletonImpl : SystemStateSingleton {

    private var curDisc = ""

    private var path = initCurDisc()

    private var isWindows = System.getProperty("os.name")
        .lowercase(Locale.getDefault()).startsWith("windows")

    override fun getPath(): String {
        if (isWindows) {
            return File(curDisc + "\\" + path).absolutePath
        } else {
            return File(path).absolutePath
        }
    }

    override fun setPath(path: String) {

        if (isWindows) {
            var dirs = path.split("\\")
            curDisc = dirs.get(0)
        }

        this.path = path.substring(curDisc.length)
    }

    override fun getFullPath(path: String): String {
        var res = path
        if (!res.startsWith("/")) {
            res = this.path + "/" + path
        }

        if (isWindows) {
            res = curDisc + "\\" + res
        }

        return File(res).absolutePath
    }

    private fun initCurDisc(): String {
        var path = System.getProperty("user.dir")

        if (path.startsWith("/")) {
            return path
        } else {
            var dirs = path.split("\\")
            curDisc = dirs.get(0)
        }

        return path.substring(curDisc.length)
    }

    companion object {
        val instance = SystemStateSingletonImpl()
    }
}