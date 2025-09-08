package com.example.practicalistacompleta

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    Column(
        modifier = modifier.padding(16.dp)
    ) {
        OutlinedTextField(
            value = person.name,
            onValueChange = {},
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = person.lastName,
            onValueChange = {},
            label = { Text("Apellido") },
            modifier = Modifier.fillMaxWidth()

        )
        OutlinedTextField(
            value = person.phone,
            onValueChange = {},
            label = { Text("Teléfono") },
            modifier = Modifier.fillMaxWidth()

        )
        OutlinedTextField(
            value = person.address,
            onValueChange = {},
            label = { Text("Dirección") },
            maxLines = 4,
            minLines = 2,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = { /* Guardar cambios */ },
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

@Preview(showBackground = true)
@Composable
fun PersonFormPreview() {
    PracticaListaCompletaTheme {
        PersonForm(
            person = Person(1, "John", "Doe", "12312312", "av 123")
        )
    }
}