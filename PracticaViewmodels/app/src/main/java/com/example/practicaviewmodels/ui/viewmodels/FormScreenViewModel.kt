package com.example.practicaviewmodels.ui.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class FormScreenViewModel : ViewModel() {
    val validUsers = mapOf(
        "admin" to "admin",
        "user1" to "password1",
        "user2" to "password2"
    )
    var username = mutableStateOf("")
    val password = mutableStateOf("")
    val isUserValid = mutableStateOf(false)
    fun checkUser() {
        if (validUsers[username.value] == password.value) {
            isUserValid.value = true
        } else {
            isUserValid.value = false
        }
    }
}