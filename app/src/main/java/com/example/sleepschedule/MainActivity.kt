package com.example.sleepschedule

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(),setValue,getValue {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btCalendar.setOnClickListener {
            setInt(this, generateInfoKey("14/07/2021","numSleep"),5)
            val list0 = listOf<Int>(10,20,30,40,50)
            setIntList(this, generateInfoKey("14/07/2021","timeSleep"),list0)
            setInt(this, generateInfoKey("14/07/2021","timeGoal"),1000)
            setInt(this, generateInfoKey("12/07/2021","numSleep"),3)
            val list = listOf<Int>(30,60,50)
            setIntList(this, generateInfoKey("12/07/2021","timeSleep"),list)
            setInt(this, generateInfoKey("12/07/2021","timeGoal"),200)
            setInt(this, generateInfoKey("13/07/2021","numSleep"),2)
            val list1 = listOf<Int>(30,60)
            setIntList(this, generateInfoKey("13/07/2021","timeSleep"),list1)
            setInt(this, generateInfoKey("13/07/2021","timeGoal"),45)
            val intent = Intent(this,CalendarActivity::class.java)
            startActivity(intent)
        }
        btSleepSchedule.setOnClickListener{
            val intent = Intent(this,SleepHourActivity::class.java)
            startActivity(intent)
        }
    }
}