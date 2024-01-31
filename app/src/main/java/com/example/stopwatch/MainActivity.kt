package com.example.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {
    private var time = 0;
    private var timerTask: Timer? = null
    private var isRunning = false
    private var lap = 1

    lateinit var fab: FloatingActionButton
    lateinit var secTextView1: TextView
    lateinit var milliTextView : TextView
    lateinit var labLayout : LinearLayout
    lateinit var labButton: Button
    lateinit var resetFab : FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab = findViewById<FloatingActionButton>(R.id.fab)
        secTextView1 = findViewById<TextView>(R.id.secTextview)
        milliTextView = findViewById<TextView>(R.id.milliTextView2)
        labLayout = findViewById(R.id.labLayout)
        labButton = findViewById(R.id.labButton)
        resetFab = findViewById(R.id.resetFAB)

        fab.setOnClickListener {
            isRunning = !isRunning

            if (isRunning) {
                start()
            } else {
                pause()
            }
        }

        labButton.setOnClickListener{
            recordLapTime()
        }

        resetFab.setOnClickListener{
            reset()
        }

    }

        private fun pause() {
            fab.setImageResource(R.drawable.baseline_play_arrow_24)
            timerTask?.cancel()
        }

        private fun start() {
            fab.setImageResource(R.drawable.baseline_pause_24)

            timerTask = timer(period = 10) {
                time++
                val sec = time / 100
                val milli = time % 100
                runOnUiThread {
                    secTextView1.text = "$sec"
                    milliTextView.text = "$milli"
                }
            }
        }

        private fun recordLapTime() {
            val laptime = this.time
            val textView = TextView(this)
            textView.text = "$lap LAB : ${laptime/100}.${laptime}"

            labLayout.addView(textView, 0)
            lap++
        }
    private fun reset(){
        timerTask?.cancel()
        time = 0
        isRunning = false
        fab.setImageResource(R.drawable.baseline_play_arrow_24)
        secTextView1.text = "0"
        milliTextView.text="00"

        labLayout.removeAllViews()
        lap = 1
    }
    }


