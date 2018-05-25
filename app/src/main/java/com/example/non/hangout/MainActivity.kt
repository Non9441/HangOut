package com.example.non.hangout

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ShareActionProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), AdapterView.OnItemClickListener{

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

        actList.setOnItemClickListener(this)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, activities)
        actList.adapter = adapter

        if(sp.getInt("counter",-1) == -1) editor.putInt("counter",0); editor.commit()
        loadActivities()
    }

    fun loadActivities() {
        if(sp.getInt("counter",-1) > 0) {
            var counter = sp.getInt("counter",-1)

            for(i in 1..counter) {
                var name = sp.getString("name"+i.toString(),null)
                var descrb = sp.getString("descrb"+i.toString(),null)
                var lat = sp.getFloat("lat"+i.toString(),-1.toFloat())
                var long = sp.getFloat("long"+i.toString(),-1.toFloat())
                activities.add(Activity(name, descrb, lat, long))
            }
        }
        adapter.notifyDataSetChanged()
    }

    fun clearSP() {
        editor.clear()
        editor.commit()
    }

    fun onAddClicked(view: View) {
        var intent = Intent(this, CreateActivity::class.java)
        startActivity(intent)
    }

    fun onClearClicked(view: View) {
        clearSP()
        editor.putInt("counter",0)
        editor.commit()
        activities.clear()
        loadActivities()
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        var item: Activity = adapter.getItem(position)
        var intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("name", item.name)
        intent.putExtra("describe", item.descrb)
        intent.putExtra("latitude", item.lat)
        intent.putExtra("longitude", item.long)
        startActivity(intent)

    }

}