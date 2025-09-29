package com.example.practicabd.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.practicabd.bd.entities.Person
import com.example.practicabd.ui.theme.PracticaBDTheme
import com.example.practicabd.ui.viewmodels.HomeViewModel

@Composable
fun HomeScreen(
    navController: NavHostController = rememberNavController(),
    vm: HomeViewModel = viewModel()
) {
    val context = LocalContext.current
    val items by vm.people.collectAsState()
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(NavScreens.DETAIL.name)
                }
            ) {
                Text(text = "+")
            }
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        LaunchedEffect(Unit) {
            vm.loadPeople(context)
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            items(items) {
                PersonItem(it, vm, navController)
                HorizontalDivider(modifier = Modifier.padding(8.dp))
            }
        }
    }

}

@Composable
fun PersonItem(
    person: Person,
    vm: HomeViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),
) {
    val context = LocalContext.current
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .clickable {
                navController.navigate(NavScreens.DETAIL.name)
            }
    ) {
        Text(
            text = "${person.id} ${person.name} ${person.lastName}",
        )
        Button(
            onClick = {
                vm.deletePerson(context, person)
            }
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete"
            )
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