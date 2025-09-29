package com.example.practicabd.ui.viewmodels

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.practicabd.bd.entities.Person
import com.example.practicabd.repositories.PersonRepository

class DetailViewModel : ViewModel() {


    var name = mutableStateOf("")
    var lastName = mutableStateOf("")

    var age = mutableStateOf("")
    var city = mutableStateOf("")
    var birthDate = mutableStateOf("")
    fun savePerson(context: Context) {
        PersonRepository.insertPerson(
            context,
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