package com.example.practicalistacompleta.repositories

import com.example.practicalistacompleta.models.Person

object PersonRepository {
    private val people = arrayListOf(
        Person(1, "Juan", "Perez", "555-1234", "Calle 1"),
        Person(2, "Maria", "Gomez", "555-5678", "Calle 2"),
        Person(3, "Carlos", "Lopez", "555-8765", "Calle 3"),
        Person(4, "Ana", "Martinez", "555-4321", "Calle 4"),
        Person(5, "Luis", "Hernandez", "555-6789", "Calle 5"),
        Person(6, "Sofia", "Garcia", "555-9876", "Calle 6"),
        Person(7, "Miguel", "Rodriguez", "555-3456", "Calle 7"),
        Person(8, "Laura", "Fernandez", "555-6543", "Calle 8"),
        Person(9, "Jorge", "Sanchez", "555-7890", "Calle 9"),
        Person(
            10, "Elena", "Ramirez", "555-0987", "Calle 10"
        ),
        Person(11, "Diego", "Torres", "555-1122", "Calle 11"),
        Person(12, "Marta", "Flores", "555-2211", "Calle 12"),
        Person(13, "Andres", "Rivera", "555-3344", "Calle 13"),
        Person(14, "Cecilia", "Vargas", "555-4433", "Calle 14"),
        Person(15, "Fernando", "Castro", "555-5566", "Calle 15")
    )

    fun getPeople(): ArrayList<Person> {
        return people
    }

    fun updatePerson(person: Person) {
        val index = people.indexOfFirst { it.id == person.id }
        if (index != -1) {
            people[index] = person
        }
    }

    fun createPerson(person: Person) {
        var newId = 1
        if (people.isNotEmpty()) {
            newId = people.maxOf { it.id } + 1
        }
        person.id = newId
        people.add(person)
    }

    fun deletePerson(person: Person) {
        people.removeIf { it.id == person.id }
    }

}