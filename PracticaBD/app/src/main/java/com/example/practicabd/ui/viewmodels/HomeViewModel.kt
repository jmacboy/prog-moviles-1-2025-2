package com.example.practicabd.ui.viewmodels

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.practicabd.bd.entities.Person
import com.example.practicabd.repositories.PersonRepository
import kotlinx.coroutines.flow.MutableStateFlow

class HomeViewModel(
    private val repo: PersonRepository
) : ViewModel() {
    var people = MutableStateFlow<List<Person>>(emptyList())

    fun loadPeople() {
        people.value = repo.getAllPeople().toMutableStateList()
    }

    fun insertExamplePerson() {
        repo.insertPerson(
            Person(
                "Juan",
                "Perez",
                30,
                "Santa Cruz",
                "1993-01-01"
            )
        )
        loadPeople()
    }

    fun deletePerson(person: Person) {
        repo.deletePerson(person)
        loadPeople()
    }

}