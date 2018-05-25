package com.example.non.hangout

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
    private var latitude = 0.toFloat()
    private var longitude = 0.toFloat()
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
        mMap.addMarker(MarkerOptions().position(thai).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(thai))
        mMap.setOnMapClickListener(this)
    }

    fun onCreateClicked(view: View) {
        var name = actName.text.toString()
        var descrp = describe.text.toString()

        actName.setText(""+latitude+" ,"+longitude)

    }

}
