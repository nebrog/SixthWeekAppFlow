package com.example.sixthweekappflow

import android.graphics.Color

interface Background {
    fun play()
    fun pause()
    fun reset()

    companion object {
        const val COLOR_TIMEOUT = 5_000L
        const val TIME_TIMEOUT = 100L
        const val TIME_SLEEP_PAUSE = 100L
        val colors = listOf(
            Color.WHITE,
            Color.RED,
            Color.GREEN,
            Color.BLUE,
            Color.YELLOW,
            Color.CYAN,
            Color.DKGRAY,
            Color.GRAY,
            Color.MAGENTA

        )
    }
}