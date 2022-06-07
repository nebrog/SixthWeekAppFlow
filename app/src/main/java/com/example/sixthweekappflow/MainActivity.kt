package com.example.sixthweekappflow

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.sixthweekappflow.domain.FlowWorker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private val worker: FlowWorker = FlowWorker()

    private val piText by lazy { findViewById<TextView>(R.id.pi_text) }
    private val timeText by lazy { findViewById<TextView>(R.id.timer) }
    private val timeBackground by lazy { findViewById<View>(R.id.play_pause) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val play = findViewById<Button>(R.id.play)
        val pause = findViewById<Button>(R.id.pause)
        val reset = findViewById<Button>(R.id.reset)
        play.setOnClickListener { worker.play() }
        pause.setOnClickListener { worker.pause() }
        reset.setOnClickListener { worker.reset() }
        lifecycleScope.launchWhenStarted {
            worker.colorFlow.collect { color ->
                withContext(Dispatchers.Main) {
                    timeBackground.setBackgroundColor(color)
                }
            }
        }
        lifecycleScope.launchWhenStarted {
            worker.timerFlow.collect { timeMs ->
                withContext(Dispatchers.Main) {
                    val millis = timeMs % 1000
                    val minutes = timeMs / 1000 / 60
                    val seconds = timeMs / 1000 % 60
                    timeText.text =
                        "${"%02d".format(minutes)}:${"%02d".format(seconds)}:${"%03d".format(millis)}"
                }
            }
        }
        lifecycleScope.launchWhenStarted {
            worker.piFlow.collect { pi ->
                withContext(Dispatchers.Main) {
                    piText.text = pi
                }

            }
        }
    }

    override fun onPause() {
        super.onPause()
        worker.pause()
    }

    override fun onResume() {
        super.onResume()
        worker.play()
    }
}