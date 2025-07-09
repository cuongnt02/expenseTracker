package com.ntc.expenseTracker.utils

import okio.FileSystem
import okio.Path
import okio.buffer

fun writeJSON(path: Path, content: () -> String) {
    FileSystem.SYSTEM.write(path) {
        writeUtf8(content())
    }
}

fun readJSON(path: Path): String {
    var json = ""
    FileSystem.SYSTEM.source(path).use { fileSource ->
        fileSource.buffer().use { bufferedSource ->
            while (true) {
                val line = bufferedSource.readUtf8Line() ?: break
                json += line
            }
        }
    }
    return json
}