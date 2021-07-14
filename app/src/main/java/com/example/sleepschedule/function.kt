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

fun sumList(list: List<Int>):Int{
    var sum = 0
    for (i in list.indices) {
        sum += list[i]
    }
    return sum
}

fun checkAccept(sleepTime: List<Int>,goalTime: List<Int>):Boolean{
    val total_sleepTime = sumList(sleepTime)
    val total_goalTime = sumList(goalTime)
    if (total_goalTime == 0) return false
    if (total_sleepTime*100>=(total_goalTime*75)){
        return true
    }
    return false
}

fun dayOfMonth(month: Int, year: Int):Int{
    if (month==1 || month==3 || month==5 || month==7 || month==8 || month==10 || month==12){
        return 31
    }
    else if (month == 2) {
        if (year % 100 == 0) {
            if (year % 400 == 0) {
                return 29
            } else {
                return 28
            }
        } else {
            if (year % 4 == 0) {
                return 29
            } else {
                return 28
            }
        }
    }
    else{
        return 30
    }
    return 0
}