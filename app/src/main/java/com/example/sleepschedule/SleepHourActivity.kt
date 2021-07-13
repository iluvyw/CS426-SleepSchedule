package com.example.sleepschedule

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.provider.AlarmClock
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.stefanodp91.android.circularseekbar.CircularSeekBar
import com.github.stefanodp91.android.circularseekbar.OnCircularSeekBarChangeListener
import kotlinx.android.synthetic.main.activity_sleep_hour.*
import java.text.SimpleDateFormat
import java.util.*


class SleepHourActivity : AppCompatActivity(), setValue, getValue {
    private var minute: Int = 0 // Save Current MinuteSleepGoal
    private var hour: Int = 0 // Save Current HourSleepGoal
    private var isFullGoal: Boolean = true
    private var todayTime: Calendar = Calendar.getInstance()
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
//        countUpTimer.base = SystemClock.elapsedRealtime()
//        countUpTimer.start()
//        countUpTimer.visibility = View.VISIBLE
        seek.isEnabled = false
        buttonEnd.visibility = View.VISIBLE

        setAlarm()

        sendFirstData();
        // Create Countdown Timer
        val startTime = (1000 * 60 * (hour * 60 + minute)).toLong()
        val timer = object : CountDownTimer(startTime, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                if (isFullGoal) {
                    var tmp_min: Int = (millisUntilFinished.div(60000)).toInt()
                    seek.progress = minuteToProgress(tmp_min)
                    var tmp_hour: Int = tmp_min / 60
                    tmp_min -= tmp_hour * 60
                    seek.text = if (tmp_min < 10) "$tmp_hour:0$tmp_min" else "$tmp_hour:$tmp_min"
                }
            }

            override fun onFinish() {
                if (isFullGoal) {
                    sendLastData()
                }
            }
        }
        timer.start()

    }

    var today = SimpleDateFormat("dd/MM/yyyy").format(Date())
    private fun sendFirstData() {

        val c: Calendar = Calendar.getInstance()
        todayTime = c
        val cur_min = c.get(Calendar.HOUR_OF_DAY) * 60 + c.get(Calendar.MINUTE)
        val goal_min = hour * 60 + minute
        val timeStartSleep: MutableList<Int> = getIntList(this, today + "timeStartSleep")
        val timeSleep: MutableList<Int> = getIntList(this, today + "timeSleep")
        val timeGoal: MutableList<Int> = getIntList(this, today + "timeGoal")

        timeStartSleep.add(cur_min)
        timeGoal.add(goal_min)
        setIntList(this, today + "timeStartSleep", timeStartSleep)
        setIntList(this, today + "timeGoal", timeGoal)
    }

    private fun setAlarm() {
        val c: Calendar = Calendar.getInstance()
        c.add(Calendar.MINUTE, minute)
        c.add(Calendar.HOUR_OF_DAY, hour)
        isFullGoal = true
        val t = Toast.makeText(
            this,
            "Set Alarm For ${c.get(Calendar.HOUR_OF_DAY)}:${c.get(Calendar.MINUTE)}",
            Toast.LENGTH_LONG
        )
        t.show()

        val intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE, "TIME TO WAKEUP !!!")
            putExtra(AlarmClock.EXTRA_HOUR, c.get(Calendar.HOUR_OF_DAY))
            putExtra(AlarmClock.EXTRA_MINUTES, c.get(Calendar.MINUTE))
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
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

    fun endCountTime(view: View) {
        seek.progress = 0.toFloat()
        seek.text = "Hour"
        val t = Toast.makeText(
            this,
            "Alarm Cancelled",
            Toast.LENGTH_LONG
        )
        t.show()

        sendLastData()
        isFullGoal = false


        val alarmIntent = Intent(AlarmClock.ACTION_DISMISS_ALARM)
        alarmIntent.apply { putExtra(AlarmClock.ALARM_SEARCH_MODE_LABEL, "TIME TO WAKEUP !!!") }
        startActivity(alarmIntent)
    }

    private fun sendLastData() {
        val c: Calendar = Calendar.getInstance()
        val diff: Long = c.timeInMillis - todayTime.timeInMillis
        val timeSleep = getIntList(this, today + "timeSleep")
        timeSleep.add((diff / 60000).toInt())
        setIntList(this, today + "timeSleep", timeSleep)
    }
}