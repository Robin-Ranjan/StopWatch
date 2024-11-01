package org.example.project

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        Box(contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()){
            val stopWatch = remember { StopWatch() }
            StopWatchUi(
                formattedTime = stopWatch.formatedTime,
                onStartClick = stopWatch::start,
                onPauseClick = stopWatch::pause,
                onResetClick = stopWatch::reset
            )
        }
    }
}