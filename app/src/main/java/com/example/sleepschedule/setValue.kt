package com.example.sleepschedule

import android.content.Context

interface setValue {
    fun setInt(context: Context,key: String,value: Int){
        val sharedPref = context.getSharedPreferences("myPreference",Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.apply{
            putInt(key, value)
            apply()
        }
    }

    fun setString(context: Context,key: String,value: String){
        val sharedPref = context.getSharedPreferences("myPreference",Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.apply{
            putString(key, value)
            apply()
        }
    }

    fun setBoolean(context: Context,key: String,value: Boolean){
        val sharedPref = context.getSharedPreferences("myPreference",Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.apply{
            putBoolean(key, value)
            apply()
        }
    }

    fun setIntList(context: Context, key: String, values: List<Int>){
        val sharedPref = context.getSharedPreferences("myPreference",Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        for (i in values.indices){
            editor.apply{
                putInt(generateKey(key,i),values[i])
                apply()
            }
        }
        editor.putInt(key+"Size",values.size)
        editor.apply()
    }

    fun setStringList(context: Context, key: String, values: List<String>){
        val sharedPref = context.getSharedPreferences("myPreference",Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        for (i in values.indices){
            editor.apply{
                putString(generateKey(key,i),values[i])
                apply()
            }
        }
        editor.putInt(key+"Size",values.size)
        editor.apply()
    }


}


