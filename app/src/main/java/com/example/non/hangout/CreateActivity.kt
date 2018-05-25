package com.example.non.hangout

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.non.hangout.R.id.actName
import com.example.non.hangout.R.id.describe
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_create.*

class CreateActivity : FragmentActivity(), OnMapReadyCallback, GoogleMap.OnMapClickListener {

    lateinit var mMap : GoogleMap
    private var latitude = 15.8700.toFloat()
    private var longitude = 100.9925.toFloat()
    final val sharedName = "HangOut_PREF"

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    override fun onMapClick(point: LatLng?) {
        mMap.clear()
        mMap.addMarker(MarkerOptions().position(point!!))
        latitude = point.latitude.toFloat()
        longitude = point.longitude.toFloat()
    }


    override fun onMapReady(googleMap: GoogleMap?) {
        mMap = googleMap!!
        // Add a marker in Sydney and move the camera
        val thai = LatLng(15.8700, 100.9925)
        mMap.addMarker(MarkerOptions().position(thai).title("Marker in Thai"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(thai))
        mMap.setOnMapClickListener(this)
    }

    fun onCreateClicked(view: View) {
        var name = actName.text.toString()
        var descrb = describe.text.toString()

        var sp = getSharedPreferences(sharedName, Context.MODE_PRIVATE)
        var editor = sp.edit()

        var counter = sp.getInt("counter",-1)
        editor.putString("name"+(counter+1),name)
        editor.putString("descrb"+(counter+1),descrb)
        editor.putFloat("lat"+(counter+1),latitude)
        editor.putFloat("long"+(counter+1),longitude)
        editor.putInt("counter",counter+1)

        editor.commit()

        var intent = Intent(this, MainActivity::class.java)

        startActivity(intent)

    }

}
