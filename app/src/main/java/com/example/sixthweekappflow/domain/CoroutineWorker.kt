package com.example.sixthweekappflow.domain

import com.example.sixthweekappflow.Background
import com.example.sixthweekappflow.Background.Companion.COLOR_TIMEOUT
import com.example.sixthweekappflow.Background.Companion.TIME_SLEEP_PAUSE
import com.example.sixthweekappflow.Background.Companion.TIME_TIMEOUT
import com.example.sixthweekappflow.Background.Companion.colors
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import java.util.*
import kotlin.system.measureTimeMillis

class CoroutineWorker() : Background {

    @Volatile
    private var isPaused = false
    private val random = Random(System.currentTimeMillis())

    @Volatile
    private var sleepTime = 0L

    @Volatile
    private var timeMillis = 0L

    val colorFlow = flow<Int> {
        while (true) {
            while (sleepTime < COLOR_TIMEOUT) {
                delay(TIME_SLEEP_PAUSE)
                if (!isPaused) {
                    sleepTime += TIME_SLEEP_PAUSE
                }
            }
            val colorIndex = random.nextInt(colors.size)
            val color = colors.get(colorIndex)
            emit(color)
            sleepTime = 0
        }
    }
    val timerFlow = flow<Long> {
        while (true) {
            while (isPaused) {
                delay(TIME_SLEEP_PAUSE)
            }
            delay(TIME_TIMEOUT)
            timeMillis += TIME_TIMEOUT
            if (!isPaused) {
                val measure = measureTimeMillis {
                    emit(timeMillis)
                }
                timeMillis += measure
            }
        }
    }


    override fun pause() {
        isPaused = true
    }

    override fun play() {
        isPaused = false
    }

    override fun reset() {
        sleepTime = 0
        timeMillis = 0
        isPaused = false


    }
}