package com.example.practicabd.ui.viewmodels

import android.content.Context
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.practicabd.bd.entities.Person
import com.example.practicabd.repositories.PersonRepository
import kotlinx.coroutines.flow.MutableStateFlow

class HomeViewModel() : ViewModel() {
    var people = MutableStateFlow<List<Person>>(emptyList())

    fun loadPeople(context: Context) {
        people.value = PersonRepository.getAllPeople(context).toMutableStateList()
    }

    fun insertExamplePerson(context: Context) {
        PersonRepository.insertPerson(
            context, Person(
                "Juan",
                "Perez",
                30,
                "Santa Cruz",
                "1993-01-01"
            )
        )
        loadPeople(context)
    }

    fun deletePerson(context: Context, person: Person) {
        PersonRepository.deletePerson(context, person)
        loadPeople(context)
    }

}