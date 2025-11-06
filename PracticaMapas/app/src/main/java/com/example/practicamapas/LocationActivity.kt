package com.example.practicamapas

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.practicamapas.ui.theme.PracticaMapasTheme
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState

class LocationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        MapsInitializer.initialize(this)
        setContent {
            PracticaMapasTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        LocationUpdatesScreen(LocationUpdates(this@LocationActivity))

                    }
                }
            }
        }
    }
}

@Composable
fun LocationUpdatesScreen(locationUpdates: LocationUpdates) {

    val viewModel: MapViewModel = viewModel()


    val location by locationUpdates.currentLocation.collectAsState()
    var hasLocationPermission by remember { mutableStateOf(false) }


    val context = LocalContext.current


    val locationPermissions = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
    ) { permissions ->
        if (permissions.values.all { it }) {
            locationUpdates.startLocationUpdates()
            hasLocationPermission = true
        } else {
            hasLocationPermission = false
            Toast.makeText(context, "No se dieron los permisos", Toast.LENGTH_SHORT).show()
        }
    }


    DisposableEffect(locationUpdates) {
        val hasFineLocation = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        if (hasFineLocation) {
            hasLocationPermission = true
            locationUpdates.startLocationUpdates()
        } else {
            permissionLauncher.launch(locationPermissions)
        }


        onDispose {
            //stop location updates
            locationUpdates.stopLocationUpdates()
        }
    }

    val markerIcon = remember {
        BitmapDescriptorFactory.fromResource(R.drawable.car)
    }
    val currentLatLong = location?.let { LatLng(it.latitude, it.longitude) } ?: LatLng(1.0, 1.0)

    val cammeraPossionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(currentLatLong, 15f)
    }

    Column {
        if (location == null) {
            Text("loading location")
        } else {
            val origin = LatLng(location?.latitude!!, location?.longitude!!)
            val destination = LatLng(-17.365319662533867, -66.154527489858)

            var routePoints by viewModel.routePoints
            Text("latitude: ${location?.latitude}")
            Text("longitude: ${location?.longitude}")
            Button(onClick = {
                viewModel.getDirection(origin, destination)
            }) {
                Text("getDIRECTIONS")
            }






            GoogleMap(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                properties = MapProperties(isMyLocationEnabled = hasLocationPermission),
                cameraPositionState = cammeraPossionState
            ) {
                Marker(
                    state = MarkerState(position = currentLatLong),
                    title = "My current location",
                    snippet = "My current location: Lat: ${currentLatLong.latitude}, Long: ${currentLatLong.longitude}",
//                    icon = markerIcon
                )

                if (routePoints.isNotEmpty()) {
                    Polyline(points = routePoints, color = Color.Blue, width = 5f)
                }
            }
        }
    }


}