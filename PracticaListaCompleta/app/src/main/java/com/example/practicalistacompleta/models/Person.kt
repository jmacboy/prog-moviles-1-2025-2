package com.example.practicalistacompleta.models

import java.io.Serializable

class Person(
    var id: Int,
    var name: String,
    var lastName: String,
    var phone: String,
    var address: String
): Serializable