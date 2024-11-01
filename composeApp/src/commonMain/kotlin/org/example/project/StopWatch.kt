package org.example.project

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

class StopWatch {
    var formatedTime by mutableStateOf("00:00:000")

    private var isActive = false
    private var coroutineScope = CoroutineScope(Dispatchers.Main)

    private var timeMillis = 0L
    private var lastTimeStamp = 0L

    fun start() {
        if (isActive) return

        coroutineScope.launch {
            lastTimeStamp = System.currentTimeMillis()
            this@StopWatch.isActive = true
            while (this@StopWatch.isActive) {
                delay(10L)
                timeMillis += System.currentTimeMillis() - lastTimeStamp
                lastTimeStamp = System.currentTimeMillis()
                formatedTime = formatTime(timeMillis)
            }
        }
    }

    private fun formatTime(timeMillis: Long): String {
//        val localDateTime = LocalDateTime.ofInstant(
//            Instant.ofEpochMilli(timeMillis),
//            ZoneId.systemDefault()
//        )
//        val formatter = DateTimeFormatter.ofPattern(
//            "mm:ss:SSS",
//            Locale.getDefault()
//        )
//        return localDateTime.format(formatter)
        val minutes = (timeMillis / 1000) / 60
        val seconds = (timeMillis / 1000) % 60
        val millis = timeMillis % 1000
        return String.format("%02d:%02d:%03d", minutes, seconds, millis)
    }

    fun pause() {
        isActive = false
//        coroutineScope.cancel()
//        coroutineScope = CoroutineScope(Dispatchers.Main)
    }

    fun reset() {
        coroutineScope.cancel()
        coroutineScope = CoroutineScope(Dispatchers.Main)
        timeMillis = 0L
        lastTimeStamp = 0L
        formatedTime = "00:00:000"
        isActive = false
    }
}