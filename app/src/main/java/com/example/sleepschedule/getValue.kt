package com.example.sleepschedule

import android.content.Context
import android.widget.Toast

interface getValue {
    fun getInt(context: Context,key: String) : Int{
        val sharedPref = context.getSharedPreferences("myPreference",Context.MODE_PRIVATE)
        return sharedPref.getInt(key,0)
    }

    fun getString(context: Context,key: String) : String?{
        val sharedPref = context.getSharedPreferences("myPreference",Context.MODE_PRIVATE)
        return sharedPref.getString(key,null)
    }

    fun getBoolean(context: Context,key: String) : Boolean{
        val sharedPref = context.getSharedPreferences("myPreference",Context.MODE_PRIVATE)
        return sharedPref.getBoolean(key, false)
    }

    fun getIntList(context: Context,key: String) : List<Int>{
        val sharedPref = context.getSharedPreferences("myPreference",Context.MODE_PRIVATE)
        val returnList = mutableListOf<Int>()
        val size = sharedPref.getInt(key+"Size",0)
        for (i in 0 until size){
            val num = sharedPref.getInt(generateKey(key, i),0)
            returnList.add(num)
        }
        return returnList
    }

    fun getStringList(context: Context,key: String) : List<String?>{
        val sharedPref = context.getSharedPreferences("myPreference",Context.MODE_PRIVATE)
        val returnList = mutableListOf<String?>()
        val size = sharedPref.getInt(key+"Size",0)
        for (i in 0 until size){
            val string = sharedPref.getString(generateKey(key, i),null)
            returnList.add(string)
        }
        return returnList
    }
}