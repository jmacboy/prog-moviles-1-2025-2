package com.example.practicalistacompleta.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.practicalistacompleta.models.Person
import com.example.practicalistacompleta.repositories.PersonRepository
import com.example.practicalistacompleta.ui.theme.PracticaListaCompletaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticaListaCompletaTheme {
                Scaffold(
                    floatingActionButton = {
                        ActionButtons()
                    },
                    modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PersonList(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ActionButtons() {
    val context = LocalContext.current
    FloatingActionButton(onClick = {
        val intent = Intent(context, PersonDetailActivity::class.java)
        context.startActivity(intent)
    }) {
        Text("+")
    }
}

@Composable
fun PersonList(modifier: Modifier = Modifier) {
    var people = PersonRepository.getPeople()

    val lifecycleOwner = LocalLifecycleOwner.current
    val lifecycleState by lifecycleOwner.lifecycle.currentStateFlow.collectAsState()
    when (lifecycleState) {
        androidx.lifecycle.Lifecycle.State.RESUMED -> {
            people = PersonRepository.getPeople()
        }
        else -> {
            // No hacer nada
        }
    }


    LazyColumn(modifier = modifier.padding(16.dp)) {
        items(people) { person ->
            PersonItem(person)
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}

@Composable
fun PersonItem(person: Person) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
            val intent = Intent(context, PersonDetailActivity::class.java)
            intent.putExtra("person", person)
            context.startActivity(intent)
        }
    ) {
        Text(person.name + " " + person.lastName)
        Text(person.phone)
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PracticaListaCompletaTheme {
        PersonList()
    }
}