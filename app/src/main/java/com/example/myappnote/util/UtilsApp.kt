package com.example.myappnote.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object UtilsApp {
    @RequiresApi(Build.VERSION_CODES.O)
    fun parseDatePatter(): String {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
    }
}