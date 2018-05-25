package com.example.non.hangout

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ShareActionProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    final val sharedName = "HangOut_PREF"
    lateinit var sp: SharedPreferences
    lateinit var editor: SharedPreferences.Editor

    var activities = ArrayList<Activity>()
    lateinit var adapter: ArrayAdapter<Activity>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sp = getSharedPreferences(sharedName, Context.MODE_PRIVATE)
        editor = sp.edit()
        if(sp.getInt("counter",-1) == -1) editor.putInt("counter",0)
        loadActivities()
    }

    fun loadActivities() {
        if(sp.getInt("counter",-1) > 0) {
            var counter = sp.getInt("counter",-1)

            for(i in 1..counter) {
                var name = sp.getString("name"+i,null)
                var descrp = sp.getString("descrp"+i,null)
                var lat = sp.getFloat("lat"+i,-1.toFloat())
                var long = sp.getFloat("long"+i,-1.toFloat())
                activities.add(Activity(name, descrp, lat, long))
            }

            adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, activities)
            actList.adapter = adapter
        }
    }

    fun onAddClicked(view: View) {
        var intent = Intent(this, CreateActivity::class.java)
        startActivity(intent)
    }

    fun onClearClicked(view: View) {
        editor.putInt("counter",0)
        activities.clear()
        loadActivities()
    }

}