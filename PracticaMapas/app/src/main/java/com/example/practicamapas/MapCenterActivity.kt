package com.example.practicamapas

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
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
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberUpdatedMarkerState

class MapCenterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MapsInitializer.initialize(this)
        enableEdgeToEdge()
        setContent {
            PracticaMapasTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        ComposeMapCentered()
                    }
                }
            }
        }
    }
}


@Composable
fun ComposeMapCentered() {

    val singapore = LatLng(-17.36417, -66.15995)
    val singaporeMarkerState = rememberUpdatedMarkerState(position = singapore)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(singapore, 15f)
    }


    val viewModel: MapViewModel = viewModel()

    val list by viewModel.markerList.collectAsState()
    val markerData by viewModel.showMarkInfo.collectAsState()

    var hasLocationPermission by remember { mutableStateOf(false) }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
    ) { permissions ->
        hasLocationPermission =
            permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true && permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true
    }


    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                permissionLauncher.launch(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                )
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer = observer)
        onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
    }


    val markerIcon = remember {
        BitmapDescriptorFactory.fromResource(R.drawable.custommarker)
    }


    if (hasLocationPermission.not()) {
        Text(text = "No location permission")
    } else {
        Box(modifier = Modifier.fillMaxSize()) {


            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                properties = MapProperties(isMyLocationEnabled = hasLocationPermission),
                onMapLongClick = { data ->
                    viewModel.addMarker(data)
                }
            ) {


                list.forEach {
                    Marker(
                        state = MarkerState(position = it),
                        onClick = {
                            viewModel.showMarkInfo(it.position.toString())
                            false

                        },
                        // icon =  markerIcon
                    )
                }

                if (markerData != null) {
                    AlertDialog(onDismissRequest = {}, confirmButton = {
                        Button(onClick = {
                            viewModel.dismissMarkInfo()
                        }) {
                            Text("ok")
                        }
                    }, title = { Text("Data") }, text = {
                        Text(markerData ?: "")
                    })
                }
            }

                Image(
                    painter = painterResource(
                        id = R.drawable.custommarker
                    ),
                    contentDescription = "Centered marker",
                    modifier = Modifier.align(androidx.compose.ui.Alignment.Center)
                        .size(
                            48.dp
                        )
                )
            Button(
                onClick = {
                    val centerPosition = cameraPositionState.position.target
                    viewModel.addMarker(centerPosition)
                },
                modifier = Modifier
                    .align(androidx.compose.ui.Alignment.BottomCenter)
                    .padding(16.dp)
            ) {
                Text("Marcar posicion central")
            }
        }

    }


}


