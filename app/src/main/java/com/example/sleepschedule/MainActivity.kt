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
            /*var timeSleep = mutableListOf<Int>(10,20,30,40)
            var timeStartSleep = mutableListOf<Int>(1,2,3,4,5)
            var timeGoal = mutableListOf<Int>(10,20,30,40)
            setIntList(this, generateInfoKey("14/07/2021","timeSleep"),timeSleep)
            setIntList(this, generateInfoKey("14/07/2021","timeStartSleep"),timeStartSleep)
            setIntList(this, generateInfoKey("14/07/2021","timeGoal"),timeGoal)

            timeSleep = mutableListOf<Int>(20,30,40,50)
            timeStartSleep = mutableListOf<Int>(1,2,3,4,5)
            timeGoal = mutableListOf<Int>(50,50,50,50)
            setIntList(this, generateInfoKey("13/07/2021","timeSleep"),timeSleep)
            setIntList(this, generateInfoKey("13/07/2021","timeStartSleep"),timeStartSleep)
            setIntList(this, generateInfoKey("13/07/2021","timeGoal"),timeGoal)

            timeSleep = mutableListOf<Int>(30,20,10,40)
            timeStartSleep = mutableListOf<Int>(1,2,3,4,5)
            timeGoal = mutableListOf<Int>(100,1000,100,100)
            setIntList(this, generateInfoKey("12/07/2021","timeSleep"),timeSleep)
            setIntList(this, generateInfoKey("12/07/2021","timeStartSleep"),timeStartSleep)
            setIntList(this, generateInfoKey("12/07/2021","timeGoal"),timeGoal)*/

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