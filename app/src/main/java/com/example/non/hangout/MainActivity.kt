package com.example.non.hangout

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

class MainActivity : AppCompatActivity() {

    final val sharedName = "HangOut_PREF"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var sp = getSharedPreferences(sharedName, Context.MODE_PRIVATE)
        var editor = sp.edit()
        if(sp.getInt("counter",-1) == -1) editor.putInt("counter",0)
        if(sp.getInt("counter",-1) > 0) {

        }
    }

    fun onAddClicked(view: View) {
        var intent = Intent(this, CreateActivity::class.java)
        startActivity(intent)
    }

}