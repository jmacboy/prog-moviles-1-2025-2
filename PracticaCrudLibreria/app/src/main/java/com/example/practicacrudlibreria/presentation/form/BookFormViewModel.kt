package com.example.practicacrudlibreria.presentation.form

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicacrudlibreria.data.remote.models.NewBookDto
import com.example.practicacrudlibreria.presentation.models.BookFormUI
import com.example.practicacrudlibreria.presentation.models.BookUI
import com.example.practicacrudlibreria.repositories.BookRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BookFormViewModel : ViewModel() {


    var _bookFormUI: MutableStateFlow<BookFormUI> = MutableStateFlow(BookFormUI())

    val bookFormUI = _bookFormUI.asStateFlow()
    val repository = BookRepository()

    fun submitBook(
        bookId: Int,
        onSuccess: () -> Unit
    ) =
        viewModelScope.launch {
            if (bookId == -1){
                repository.createBook(
                    NewBookDto(
                        nombre = _bookFormUI.value.nombre,
                        sinopsis = _bookFormUI.value.sinopsis,
                        autor = _bookFormUI.value.autor,
                        editorial = _bookFormUI.value.editorial,
                        isbn = "9788498386325",
                        imagen = "https://encantalibros.com/wp-content/uploads/2020/12/9788498386318.jpg"
                )).fold(
                    onSuccess ={
                        onSuccess()
                    },
                    onFailure = {}
                )
            }else {
                repository.updateBook(
                    bookId,
                    NewBookDto(
                        nombre = _bookFormUI.value.nombre,
                        sinopsis = _bookFormUI.value.sinopsis,
                        autor = _bookFormUI.value.autor,
                        editorial = _bookFormUI.value.editorial,
                        isbn =  _bookFormUI.value.isbn,
                        imagen = _bookFormUI.value.imagen
                    )
                ).fold(
                    onSuccess ={
                        onSuccess()
                    },
                    onFailure = {}
                )
            }
        }



    fun fetchBook(bookId: Int) = viewModelScope.launch {

        val res = repository.getBookById(bookId)
        res.fold(
            onSuccess = {data ->
                _bookFormUI.update {
                    BookFormUI(
                        autor = data.autor,
                        calificacion = data.calificacion,
                        editorial =  data.editorial,
                        imagen = data.imagen,
                        isbn = data.isbn,
                        nombre = data.nombre,
                        sinopsis = data.sinopsis,
                    )
                }

            },
            onFailure = {

            }
        )
    }

    fun onNameChange(value: String)  = viewModelScope.launch{
        _bookFormUI.update {
            it.copy(
                nombre = value
            )
        }
    }

    fun onSinopsisChange(value: String)  = viewModelScope.launch{
        _bookFormUI.update {
            it.copy(
                sinopsis = value
            )
        }
    }

    fun onAutorChange(value: String)  = viewModelScope.launch{
        _bookFormUI.update {
            it.copy(
                autor = value
            )
        }
    }

    fun onEditorialChange(value: String)  = viewModelScope.launch{
        _bookFormUI.update {
            it.copy(
                editorial = value
            )
        }
    }


}
