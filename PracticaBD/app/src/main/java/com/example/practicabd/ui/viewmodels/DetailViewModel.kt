package com.example.practicabd.ui.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.practicabd.bd.entities.Person
import com.example.practicabd.repositories.PersonRepository

class DetailViewModel(
    val personId: Int?,
    private val repo: PersonRepository
) : ViewModel() {

    var name = mutableStateOf("")
    var lastName = mutableStateOf("")

    var age = mutableStateOf("")
    var city = mutableStateOf("")
    var birthDate = mutableStateOf("")

    init {
        if (personId != null) {
            getPersonById(personId)
        }
    }

    private fun getPersonById(personId: Int) {
        val person = repo.getPersonById(personId)
        if (person == null) return
        name.value = person.name
        lastName.value = person.lastName
        age.value = person.age.toString()
        city.value = person.city
        birthDate.value = person.birthDate

    }

    fun savePerson() {
        if (personId != null) {
            val person = Person(
                name = name.value,
                lastName = lastName.value,
                age = age.value.toIntOrNull() ?: 0,
                city = city.value,
                birthDate = birthDate.value
            )
            person.id = personId
            repo.updatePerson(
                person
            )
        } else {
            repo.insertPerson(
                Person(
                    name = name.value,
                    lastName = lastName.value,
                    age = age.value.toIntOrNull() ?: 0,
                    city = city.value,
                    birthDate = birthDate.value
                )
            )
        }
    }
}