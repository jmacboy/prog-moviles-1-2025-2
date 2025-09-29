package com.example.practicabd

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.practicabd.bd.entities.Person
import com.example.practicabd.repositories.PersonRepository
import com.example.practicabd.ui.theme.PracticaBDTheme
import java.util.Calendar
import java.util.Date

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticaBDTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var reloadPage by rememberSaveable { mutableStateOf(1) }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(
            onClick = {
                PersonRepository.insertPerson(
                    context, Person(
                        "Juan",
                        "Perez",
                        30,
                        "Santa Cruz",
                        "1993-01-01"
                    )
                )
                reloadPage = reloadPage + 1
            }
        ) {
            Text(text = "Insertar Persona")
        }
        if (reloadPage > 0) {
            val people = PersonRepository.getAllPeople(context)
            LazyColumn {
                items(people) {
                    Text(text = "${it.id} ${it.name} ${it.lastName} - ${it.age} años - ${it.city} - Nacido el ${it.birthDate}")
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    PracticaBDTheme {
        HomeScreen()
    }
}