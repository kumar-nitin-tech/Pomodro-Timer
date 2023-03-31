package com.example.pomodrotimer

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import java.text.DecimalFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var textView:TextView
    private lateinit var Startbutton: Button
    private lateinit var resetButton: Button
    private lateinit var countDownTimer: CountDownTimer
    private var startTiming = 1500000L
    private var isTimerRunning:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.timer)
        Startbutton = findViewById(R.id.startStopButton)
        Startbutton.setOnClickListener {
            if(isTimerRunning){
                stopTimer()
            }
            else{
                startTimer()
            }
        }
        resetButton = findViewById(R.id.resumePauseButton)
        resetButton.setOnClickListener{
            resetTime()
        }
    }

    private fun resetTime() {
        startTiming = 1500000L
        updateText()
        stopTimer()
    }

    private fun startTimer() {
        isTimerRunning = true
        Startbutton.text = getString(R.string.stop)
        countDownTimer = object :CountDownTimer(startTiming,1000){
            override fun onTick(p0: Long) {
                startTiming = p0
                updateText()
            }

            override fun onFinish() {
                isTimerRunning =false
            }

        }.start()

    }

    private fun updateText() {

        val min = (startTiming/60000)%60
        val sec = (startTiming/1000)%60
        textView.text = String.format(Locale.getDefault(),"%02d:%02d",min,sec)
    }

    private fun stopTimer() {
        countDownTimer.cancel()
        isTimerRunning = false
        Startbutton.text = getString(R.string.startAndStop)
    }

}