package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MainActivity : ComponentActivity(), OnMapReadyCallback {

    private lateinit var mapView: MapView
    private lateinit var googleMap: GoogleMap

    private val currentLocationUser = UserLocation(-33.860, 151.203)
    private val user = User("Stephan",31, arrayListOf("sushi, carne"),currentLocationUser)
    private val radius = 10.0
    private val locationService = LocationService()
    private val locationRepository = LocationRepository()



    val locationNearbyService = locationService.locationNearbyService(currentLocationUser, radius, locationRepository)
    val preferencesLocation = locationService.preferenceService(user,locationRepository)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val meuBotao: Button = findViewById(R.id.preferences)
        meuBotao.setOnClickListener {
            navigateToMapsActivity(MyApplication.context)
        }
        mapView = findViewById(R.id.mapView)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)
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
    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        googleMap.uiSettings.isZoomGesturesEnabled = true
        val minZoom = 5.0f
        val maxZoom = 15.0f
        val zoomCam = 13.5f

        val locationCurrentUser = LatLng(currentLocationUser.latitude,currentLocationUser.longitude)
        googleMap.addMarker(MarkerOptions().position(locationCurrentUser).title("Sua Localização"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(locationCurrentUser))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(locationCurrentUser, zoomCam))
        googleMap.setMinZoomPreference(minZoom)
        googleMap.setMaxZoomPreference(maxZoom)
    }
}



fun navigateToMapsActivity(context: Context) {
    val intent = Intent(context, MapsActivity::class.java)
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    context.startActivity(intent)


}
