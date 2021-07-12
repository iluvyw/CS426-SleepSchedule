package com.example.sleepschedule

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_set_alarm_time.*
import kotlinx.android.synthetic.main.activity_welcome.*
import java.util.*

class SetAlarmTime : AppCompatActivity(),setValue,getValue {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_alarm_time)
        setTime(timePicker,getInt(this,"hourRemind"),getInt(this,"minRemind"));
        timePicker.setIs24HourView(true);

        btSetAlarmTime.setOnClickListener{
            setInt(this, "hourRemind", timePicker.hour);
            setInt(this, "minRemind", timePicker.minute);
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
    private fun setTime(time_picker : TimePicker, hours: Int, minutes: Int) {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            time_picker.hour = hours
            time_picker.minute = minutes
        } else {
            time_picker.currentHour = hours
            time_picker.currentMinute = minutes
        }
    }
    private fun setAlarm(){
    }
}