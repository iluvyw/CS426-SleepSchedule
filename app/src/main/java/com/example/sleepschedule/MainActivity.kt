package com.example.sleepschedule

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(),setValue,getValue {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btCalendar.setOnClickListener {
            val intent = Intent(this,CalendarActivity::class.java)
            startActivity(intent)
        }
        btSleepSchedule.setOnClickListener{
            val intent = Intent(this,SleepHourActivity::class.java)
            startActivity(intent)
        }
        btWelcome.setOnClickListener{
            val intent = Intent(this, Welcome::class.java)
            startActivity(intent)
        }
        btSetAlarmTime.setOnClickListener{
            val intent = Intent(this, SetAlarmTime::class.java)
            startActivity(intent)
        }

        btReset.setOnClickListener {
            val sharedPref = getSharedPreferences("myPreference", Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.apply{
                clear()
                apply()
            }
        }
    }
}