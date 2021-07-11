package com.example.sleepschedule

fun generateKey(key: String,i: Int): String{
    return key+i.toString()
}

fun generateDateKey(day:Int,month:Int,year:Int):String{
    var returnString = ""
    if (day<10) returnString += "0"
    returnString += day.toString() + "/"
    if (month<10) returnString += "0"
    returnString += month.toString() + "/"
    returnString += year.toString()
    return returnString
}

fun generateInfoKey(date: String,attributes: String):String{
    return date+attributes
}