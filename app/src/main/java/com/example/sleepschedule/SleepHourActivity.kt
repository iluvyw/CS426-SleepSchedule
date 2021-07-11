package com.example.sleepschedule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.SystemClock
import android.view.View
import com.github.stefanodp91.android.circularseekbar.CircularSeekBar
import com.github.stefanodp91.android.circularseekbar.OnCircularSeekBarChangeListener
import kotlinx.android.synthetic.main.activity_sleep_hour.*

class SleepHourActivity : AppCompatActivity(), setValue, getValue {
    private var minute: Int = 0 // Save Current MinuteSleepGoal
    private var hour: Int = 0 // Save Current HourSleepGoal

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sleep_hour)


        seek.setOnRoundedSeekChangeListener(object : OnCircularSeekBarChangeListener {
            /**
             * Progress change
             * @param CircularSeekBar
             * @param progress the progress
             */

            override fun onProgressChange(CircularSeekBar: CircularSeekBar, progress: Float) {
                CircularSeekBar.text = progressToText(progress)
            }

            /**
             * Indicator touched
             * @param CircularSeekBar
             */
            override fun onStartTrackingTouch(CircularSeekBar: CircularSeekBar) {}

            /**
             * Indicator released
             * @param CircularSeekBar
             */
            override fun onStopTrackingTouch(CircularSeekBar: CircularSeekBar) {}
        })
    }

    fun startCountTime(view: View) {
        buttonStart.visibility = View.GONE
        countUpTimer.base = SystemClock.elapsedRealtime()
        countUpTimer.start()
        countUpTimer.visibility = View.VISIBLE
        seek.isEnabled = false

        // Create Countdown Timer
        val startTime = (1000 * 60 * (hour * 60 + minute)).toLong()
        val timer = object : CountDownTimer(startTime, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                var tmp_min: Int = (millisUntilFinished.div(60000)).toInt()
                seek.progress = minuteToProgress(tmp_min)
                var tmp_hour: Int = tmp_min / 60
                tmp_min -= tmp_hour * 60
                seek.text = if (tmp_min < 10) "$tmp_hour:0$tmp_min" else "$tmp_hour:$tmp_min"
            }

            override fun onFinish() {}
        }
        timer.start()

    }

    fun progressToText(progress: Float): String {
        minute = (progress * 720 / 100).toInt()
        hour = minute / 60
        minute -= hour * 60
        var string_min: String = if (minute >= 10) minute.toString() else "0$minute"
        return "$hour:$string_min"
    }

    fun minuteToProgress(minute_left: Int): Float {
        val res: Double = (minute_left * 100 * 1.0 / 720)
        return res.toFloat()
    }
}