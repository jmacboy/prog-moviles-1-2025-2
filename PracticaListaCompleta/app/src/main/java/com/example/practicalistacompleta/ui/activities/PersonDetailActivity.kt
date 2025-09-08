package com.example.practicalistacompleta.ui.activities

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.practicalistacompleta.models.Person
import com.example.practicalistacompleta.repositories.PersonRepository
import com.example.practicalistacompleta.ui.theme.PracticaListaCompletaTheme

class PersonDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val person = intent.getSerializableExtra("person") ?: Person(0, "", "", "", "")
        setContent {
            PracticaListaCompletaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PersonForm(
                        person = person as Person,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@SuppressLint("ContextCastToActivity") //TODO: Buscar solución
@Composable
fun PersonForm(
    person: Person,
    modifier: Modifier = Modifier
) {
    val activity = (LocalContext.current as? Activity)
    var personName by remember { mutableStateOf(person.name) }
    var personLastName by remember { mutableStateOf(person.lastName) }
    var personPhone by remember { mutableStateOf(person.phone) }
    var personAddress by remember { mutableStateOf(person.address) }
    Column(
        modifier = modifier.padding(16.dp)
    ) {
        OutlinedTextField(
            value = personName,
            onValueChange = {
                personName = it
            },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = personLastName,
            onValueChange = {
                personLastName = it
            },
            label = { Text("Apellido") },
            modifier = Modifier.fillMaxWidth()

        )
        OutlinedTextField(
            value = personPhone,
            onValueChange = {
                personPhone = it
            },
            label = { Text("Teléfono") },
            modifier = Modifier.fillMaxWidth()

        )
        OutlinedTextField(
            value = personAddress,
            onValueChange = {
                personAddress = it
            },
            label = { Text("Dirección") },
            maxLines = 4,
            minLines = 2,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                savePerson(person, personName, personLastName, personPhone, personAddress)
                activity?.finish()
            },
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
        ) {
            Text("Guardar")
        }
        Button(
            onClick = {
                activity?.finish()
            },
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
        ) {
            Text("Cancelar")
        }
    }
}

fun savePerson(
    person: Person,
    personName: String,
    personLastName: String,
    personPhone: String,
    personAddress: String
) {
    person.name = personName
    person.lastName = personLastName
    person.phone = personPhone
    person.address = personAddress
    if (person.id != 0) {
        PersonRepository.updatePerson(person)
    } else {
        PersonRepository.createPerson(person)
    }
}

@Preview(showBackground = true)
@Composable
fun PersonFormPreview() {
    PracticaListaCompletaTheme {
        PersonForm(
            person = Person(1, "John", "Doe", "12312312", "av 123")
        )
    }
}