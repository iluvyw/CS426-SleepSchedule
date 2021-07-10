package com.example.sleepschedule

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_calendar.*

class CalendarActivity : AppCompatActivity(),setValue,getValue {

    var numSleep = 0
    var timeSleep = listOf<Int>()
    var timeGoal = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val date = generateDateKey(dayOfMonth,month+1,year)
            numSleep = getInt(this, generateInfoKey(date,"numSleep"))
            timeSleep = getIntList(this, generateInfoKey(date,"timeSleep"))
            timeGoal = getInt(this, generateInfoKey(date,"timeGoal"))
            //tvTitle.setText(numSleep.toString()+timeGoal.toString())
            //tvTitle.setText(generateInfoKey(date,"numSleep"))
            setImageViewColor()
            setEditTextResult()
        }
    }

    private fun setEditTextResult() {
        var result = 0
        for (i in timeSleep.indices){
            result += timeSleep[i]
        }
        var percent = 0
        if (timeGoal>0){
            percent = result * 100 / timeGoal
        }
        tvResult.setText(percent.toString()+"%")
        etGoalTime.setText(timeGoal.toString())
        etYourTime.setText(result.toString())
    }

    private fun setImageViewColor() {
        var result = 0
        for (i in timeSleep.indices){
            result += timeSleep[i]
        }
        if (timeGoal == 0){
            ivResult.setBackgroundColor(Color.rgb(255,255,255))
        }
        else if (result<(0.5*timeGoal)){
            ivResult.setBackgroundColor(Color.rgb(255,0,0)) //red
        }
        else if (result<=(0.75*timeGoal)){
            ivResult.setBackgroundColor(Color.rgb(255,255,0)) //yellow
        }
        else{
            ivResult.setBackgroundColor(Color.rgb(0,255,0)) //green
        }
    }
}