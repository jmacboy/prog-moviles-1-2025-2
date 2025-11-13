package com.example.practicabd.ui.viewmodels

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicabd.bd.entities.Person
import com.example.practicabd.repositories.PersonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repo: PersonRepository
) : ViewModel() {
    var people = MutableStateFlow<List<Person>>(emptyList())

    fun loadPeople() = viewModelScope.launch {
        people.value = repo.getAllPeople().toMutableStateList()
    }

    fun insertExamplePerson() = viewModelScope.launch {
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

    fun deletePerson(person: Person) = viewModelScope.launch {
        repo.deletePerson(person)
        loadPeople()
    }

}