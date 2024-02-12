package com.example.mapas_zulema

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.mapas_zulema.fragments.FragmentMorro
import com.example.mapas_zulema.fragments.FragmentParque
import com.example.mapas_zulema.fragments.FragmentSena
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class MainActivity : AppCompatActivity(), OnMapReadyCallback, OnMarkerClickListener {

    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createFragment()
    }

    private fun createFragment(){
        val mapFragment: SupportMapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        map.setOnMarkerClickListener(this) // Aquí se establece el listener para los clics en los marcadores
        createMarker()
    }

    private fun createMarker() {
        // Punto 1: PARQUE CALDAS
        val coordinates1 = LatLng(2.4421283878032822, -76.6063805510124)
        val marker1 = MarkerOptions().position(coordinates1).title("PARQUE")
        map.addMarker(marker1)

        // Punto 2: EL MORRO
        val coordinates2 = LatLng(2.4457300675419784, -76.60025470200615)
        val marker2 = MarkerOptions().position(coordinates2).title("EL MORRO")
        map.addMarker(marker2)

        // Punto 3: SENA CTPI
        val coordinates3 = LatLng(2.4829615971267267, -76.56236119508115)
        val marker3 = MarkerOptions().position(coordinates3).title("SENA CTPI")
        map.addMarker(marker3)

        // Movimiento de la cámara al primer marcador
        map.animateCamera(
            CameraUpdateFactory.newLatLngZoom(coordinates1, 14f), 4000, null
        )
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        when(marker.title){
            "PARQUE" -> {
                openFragment(FragmentParque())
                return true
            }

            "SENA CTPI" ->{
                openFragment(FragmentSena())
                return true
            }

            "EL MORRO" ->{
                openFragment(FragmentMorro())
                return true
            }
        }
        return false


    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}
