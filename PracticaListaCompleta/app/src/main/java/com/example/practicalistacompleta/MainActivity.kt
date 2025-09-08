package com.example.practicalistacompleta

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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.practicalistacompleta.ui.theme.PracticaListaCompletaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticaListaCompletaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PersonList(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun PersonList(modifier: Modifier = Modifier) {
    val people = getPeopleList()
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

fun getPeopleList(): ArrayList<Person> {
    return arrayListOf(
        Person(1, "Juan", "Perez", "555-1234", "Calle 1"),
        Person(2, "Maria", "Gomez", "555-5678", "Calle 2"),
        Person(3, "Carlos", "Lopez", "555-8765", "Calle 3"),
        Person(4, "Ana", "Martinez", "555-4321", "Calle 4"),
        Person(5, "Luis", "Hernandez", "555-6789", "Calle 5"),
        Person(6, "Sofia", "Garcia", "555-9876", "Calle 6"),
        Person(7, "Miguel", "Rodriguez", "555-3456", "Calle 7"),
        Person(8, "Laura", "Fernandez", "555-6543", "Calle 8"),
        Person(9, "Jorge", "Sanchez", "555-7890", "Calle 9"),
        Person(
            10, "Elena", "Ramirez", "555-0987", "Calle 10"
        ),
        Person(11, "Diego", "Torres", "555-1122", "Calle 11"),
        Person(12, "Marta", "Flores", "555-2211", "Calle 12"),
        Person(13, "Andres", "Rivera", "555-3344", "Calle 13"),
        Person(14, "Cecilia", "Vargas", "555-4433", "Calle 14"),
        Person(15, "Fernando", "Castro", "555-5566", "Calle 15")
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PracticaListaCompletaTheme {
        PersonList()
    }
}