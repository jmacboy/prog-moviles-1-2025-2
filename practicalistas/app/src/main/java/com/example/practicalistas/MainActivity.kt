package com.example.practicalistas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.practicalistas.R
import com.example.practicalistas.ui.theme.PracticaListasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticaListasTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Contactlist(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MessageList(modifier: Modifier = Modifier) {
    val nameList = arrayListOf(
        "Ana", "Juan", "Pedro", "Maria", "Luis", "Carmen", "Jose", "Lucia", "Miguel", "Sofia"
    )
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(nameList) { it ->
            Text(text = it, modifier = modifier.padding(8.dp))
        }
    }
}

@Composable
fun Contactlist(
    modifier: Modifier = Modifier
) {
    val contacts = getContactList()
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(contacts) { it ->
            ContactItem(it)
            HorizontalDivider(thickness = 1.dp)
        }
    }
}

@Composable
fun ContactItem(contact: Contact) {
    Spacer(modifier = Modifier.size(8.dp))
    Row {
        Image(
            painter = painterResource(id = contact.image),
            contentDescription = "Profile Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
        )
        Text(
            contact.name + " " + contact.lastName,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }
    Spacer(modifier = Modifier.size(8.dp))

}

fun getContactList(): ArrayList<Contact> {
    return arrayListOf(
        Contact(1, "Ana", "Perez", "123456789", R.drawable.profile1),
        Contact(2, "Juan", "Gomez", "987654321", R.drawable.profile2),
        Contact(3, "Pedro", "Lopez", "456789123", R.drawable.profile3),
        Contact(4, "Maria", "Garcia", "789123456", R.drawable.profile1),
        Contact(5, "Luis", "Martinez", "321654987", R.drawable.profile2),
        Contact(6, "Carmen", "Sanchez", "654987321", R.drawable.profile3),
        Contact(7, "Jose", "Rodriguez", "147258369", R.drawable.profile1),
        Contact(8, "Lucia", "Hernandez", "369258147", R.drawable.profile2),
        Contact(9, "Miguel", "Diaz", "258147369", R.drawable.profile3),
        Contact(10, "Sofia", "Alvarez", "159753486", R.drawable.profile1),
        Contact(11, "Andres", "Torres", "357159486", R.drawable.profile2),
        Contact(12, "Elena", "Ramirez", "753951486", R.drawable.profile3),
        Contact(13, "Javier", "Flores", "951357486", R.drawable.profile1),
        Contact(14, "Laura", "Vargas", "852456123", R.drawable.profile2),
        Contact(15, "Diego", "Castro", "456123852", R.drawable.profile3),
        Contact(16, "Marta", "Rojas", "123852456", R.drawable.profile1),
        Contact(17, "Alvaro", "Mendez", "321654987", R.drawable.profile2),
        Contact(18, "Natalia", "Cruz", "987321654", R.drawable.profile3),
        Contact(19, "Sergio", "Morales", "654123789", R.drawable.profile1),
        Contact(20, "Paula", "Ortiz", "789456123", R.drawable.profile2),
    )
}

@Preview(showBackground = true)
@Composable
fun ContactListPreview() {
    PracticaListasTheme {
        Contactlist()
    }
}

@Preview(showBackground = true)
@Composable
fun ContactItemPreview() {
    PracticaListasTheme {
        ContactItem(Contact(1, "Ana", "Perez", "123456789", R.drawable.profile3))
    }
}


@Preview(showBackground = true)
@Composable
fun MessageListPreview() {
    PracticaListasTheme {
        MessageList()
    }
}