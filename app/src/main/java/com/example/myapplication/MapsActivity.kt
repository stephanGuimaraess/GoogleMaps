package com.example.myapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.myapplication.databinding.ActivityMapsBinding
import com.google.android.gms.maps.MapView

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var mapView: MapView
    val currentLocationUser = UserLocation(-33.860, 151.203)
    val radius = 10.0;
    val locationService = LocationService()
    val locationRepository =  LocationRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_maps)

        val meuButtonVoltar : Button = findViewById(R.id.voltarId)
        meuButtonVoltar.setOnClickListener {
            navagateToInitalPage(MyApplication.context)
        }
        mapView = findViewById(R.id.map)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)
    }

    private fun navagateToInitalPage(context: Context) {
        val intent = Intent(context, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val locationNearbyService = locationService.locationNearbyService(currentLocationUser, radius, locationRepository)
        for(location in locationNearbyService){
            val latLong = LatLng(location.latitude, location.longitude)
            addMarcador(latLong, location.name)
        }

    }

    private fun addMarcador(localization : LatLng, titulo : String){
        val zoomCam = 13.5f
        val minZoom = 5.0f
        val maxZoom = 15.0f
        mMap.uiSettings.isZoomGesturesEnabled = true
        mMap.addMarker(MarkerOptions().position(localization).title(titulo))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(localization))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(localization, zoomCam))
        mMap.setMinZoomPreference(minZoom)
        mMap.setMaxZoomPreference(maxZoom)

    }
}