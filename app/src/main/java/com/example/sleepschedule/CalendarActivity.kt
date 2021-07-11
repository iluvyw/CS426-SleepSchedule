package com.example.sleepschedule

import android.content.IntentSender
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_calendar.*
import kotlinx.android.synthetic.main.pop_up.view.*
import java.time.Duration
import java.util.*

class CalendarActivity : AppCompatActivity(),setValue,getValue {

    var timeStartSleep = listOf<Int>()
    var timeSleep = listOf<Int>()
    var timeGoal = listOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)
        val thisYear = Calendar.getInstance().get(Calendar.YEAR).toInt()
        val thisMonth = Calendar.getInstance().get(Calendar.MONTH).toInt()
        setEditTextResult(thisMonth+1, thisYear)

        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val date = generateDateKey(dayOfMonth,month+1,year)
            timeStartSleep = getIntList(this, generateInfoKey(date,"timeStartSleep"))
            timeSleep = getIntList(this, generateInfoKey(date,"timeSleep"))
            timeGoal = getIntList(this, generateInfoKey(date,"timeGoal"))
            //tvTitle.setText(numSleep.toString()+timeGoal.toString())
            //tvTitle.setText(generateInfoKey(date,"numSleep"))
            //setImageViewColor()
            setEditTextResult(month+1, year)
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
        view.etGoalTime.setText(sumList(timeGoal).toString())
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

        val toastCountDown = object : CountDownTimer(1000,1000){
            override fun onTick(millisUntilFinished: Long) {
                toast.show()
            }

            override fun onFinish() {
                toast.cancel()
            }
        }
        toast.show()
        toastCountDown.start()
    }

    private fun setEditTextResult(month:Int,year:Int) {
        var totalDays = 0
        var acceptDays = 0
        for (i in 1..dayOfMonth(month,year)){
            var date = generateDateKey(i,month,year)
            val timeSleep = getIntList(this, generateInfoKey(date,"timeSleep"))
            val timeGoal = getIntList(this, generateInfoKey(date,"timeGoal"))
            if (checkAccept(timeSleep,timeGoal)==true){
                acceptDays += 1
            }
            totalDays += 1
        }
        tvDateChoose.text = generateChooseDate(month,year)
        etAcceptDay.setText(acceptDays.toString())
        etTotalDays.setText(totalDays.toString())
        val percent = acceptDays*100/totalDays
        tvResult.text = percent.toString()+"%"
        if (totalDays == 0){
            ivResult.setBackgroundColor(Color.rgb(255,255,255))
        }
        else if (percent<50){
            ivResult.setBackgroundColor(Color.rgb(255,0,0)) //red
        }
        else if (percent<=75){
            ivResult.setBackgroundColor(Color.rgb(255,255,0)) //yellow
        }
        else{
            ivResult.setBackgroundColor(Color.rgb(0,255,0)) //green
        }
    }

    private fun generateChooseDate(month: Int, year: Int): String {
        var returnString:String
        when (month) {
            1 -> returnString = "January"
            2 -> returnString = "February"
            3 -> returnString = "March"
            4 -> returnString = "April"
            5 -> returnString = "May"
            6 -> returnString = "June"
            7 -> returnString = "July"
            8 -> returnString = "August"
            9 -> returnString = "September"
            10 -> returnString = "October"
            11 -> returnString = "November"
            12 -> returnString = "December"
            else -> returnString = ""
        }
        return returnString+", "+year.toString()
    }


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