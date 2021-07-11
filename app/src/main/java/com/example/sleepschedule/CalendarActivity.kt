package com.example.sleepschedule

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_calendar.*
import kotlinx.android.synthetic.main.pop_up.view.*

class CalendarActivity : AppCompatActivity(),setValue,getValue {

    var timeStartSleep = listOf<Int>()
    var timeSleep = listOf<Int>()
    var timeGoal = listOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val date = generateDateKey(dayOfMonth,month+1,year)
            timeStartSleep = getIntList(this, generateInfoKey(date,"timeStartSleep"))
            timeSleep = getIntList(this, generateInfoKey(date,"timeSleep"))
            timeGoal = getIntList(this, generateInfoKey(date,"timeGoal"))
            //tvTitle.setText(numSleep.toString()+timeGoal.toString())
            //tvTitle.setText(generateInfoKey(date,"numSleep"))
            //setImageViewColor()
            //setEditTextResult()
            popupShow()
        }
    }

    private fun popupShow(){
        val myPopUp = layoutInflater
        val view = myPopUp.inflate(R.layout.pop_up,null)

        var result = 0
        for (i in timeSleep.indices){
            result += timeSleep[i]
        }
        var percent = 0
        if (sumList(timeGoal)>0){
            percent = result * 100 / sumList(timeGoal)
        }
        view.etGoalTime.setText(timeGoal.toString())
        view.etSleepTime.setText(result.toString())

        result = 0
        for (i in timeSleep.indices){
            result += timeSleep[i]
        }
        if (sumList(timeGoal) == 0){
            view.ivResult.setBackgroundColor(Color.rgb(255,255,255))
        }
        else if (result<(0.5* sumList(timeGoal))){
            view.ivResult.setBackgroundColor(Color.rgb(255,0,0)) //red
        }
        else if (result<=(0.75* sumList(timeGoal))){
            view.ivResult.setBackgroundColor(Color.rgb(255,255,0)) //yellow
        }
        else{
            view.ivResult.setBackgroundColor(Color.rgb(0,255,0)) //green
        }

        val toast = Toast(applicationContext)
        toast.view = view
        toast.duration = Toast.LENGTH_SHORT
        toast.setGravity(Gravity.CENTER_VERTICAL,0,0)
        toast.show()
    }

    /*private fun setEditTextResult(month:Int) {
        var totalDays = 0
        var acceptDays = 0
        for (i in ){
            if (checkAccept())
        }
    }*/

    /*private fun setImageViewColor() {
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
    }*/
}