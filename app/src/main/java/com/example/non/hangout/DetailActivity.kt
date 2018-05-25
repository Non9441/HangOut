package com.example.non.hangout

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.view.View
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : FragmentActivity(), OnMapReadyCallback {
    lateinit var mMap : GoogleMap
    private var latitude = 15.8700.toFloat()
    private var longitude = 100.9925.toFloat()
    private var name = ""
    private var describe = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        name = intent.getStringExtra("name")
        describe = intent.getStringExtra("describe")
        latitude = intent.getFloatExtra("latitude",0.toFloat())
        longitude = intent.getFloatExtra("longitude",0.toFloat())

        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        actName.setText(name)
        actDes.setText(describe)

    }

    override fun onMapReady(googleMap: GoogleMap?) {
        mMap = googleMap!!
        // Add a marker in Sydney and move the camera
        val location = LatLng(latitude.toDouble(), longitude.toDouble())
        mMap.addMarker(MarkerOptions().position(location).title("Marker in Thai"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location))
    }

    fun onBackClicked(view: View) {
        var intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
