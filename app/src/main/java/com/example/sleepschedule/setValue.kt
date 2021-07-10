package com.example.sleepschedule

import android.content.Context
import android.support.v7.app.AppCompatActivity

interface setValue {
    fun setInt(context: Context,key: String,value: Int){
        val sharedPref = context.getSharedPreferences("myReference",Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.apply{
            putInt(key, value)
            commit()
        }
    }

    fun setString(context: Context,key: String,value: String){
        val sharedPref = context.getSharedPreferences("myReference",Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.apply{
            putString(key, value)
            commit()
        }
    }

    fun setBoolean(context: Context,key: String,value: Boolean){
        val sharedPref = context.getSharedPreferences("myPreference",Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.apply{
            putBoolean(key, value)
            commit()
        }
    }

    fun setIntList(context: Context, key: String, values: List<Int>){
        val sharedPref = context.getSharedPreferences("myReference",Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        for (i in values.indices){
            editor.apply{
                putInt(generateKey(key,i),values[i])
                commit()
            }
        }
        editor.putInt(key+"Size",values.size)
    }

    fun setStringList(context: Context, key: String, values: List<String>){
        val sharedPref = context.getSharedPreferences("myReference",Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        for (i in values.indices){
            editor.apply{
                putString(generateKey(key,i),values[i])
                commit()
            }
        }
        editor.putInt(key+"Size",values.size)
    }


}

fun generateKey(key: String,i: Int): String{
    return key+i.toString()
}


