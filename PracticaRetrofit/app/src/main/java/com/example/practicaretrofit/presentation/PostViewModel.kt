package com.example.practicaretrofit.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicaretrofit.presentation.models.PostUI
import com.example.practicaretrofit.repository.PostRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PostViewModel : ViewModel() {

    private val _list: MutableStateFlow<List<PostUI>> = MutableStateFlow(emptyList())
    val list: StateFlow<List<PostUI>> = _list.asStateFlow()

    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error: MutableStateFlow<String?> = MutableStateFlow(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    val repository = PostRepository()


    init {
        fetchPosts()
    }

    fun fetchPosts() = viewModelScope.launch {
        _isLoading.value = true

        val result = repository.getPostsUI()
        result.fold(
            onSuccess = { data ->
                _list.update { data }
            },
            onFailure = { e ->
                _error.update { e.toString() }
            })

        _isLoading.value = false
    }

    fun dismissError() {
        _error.value = null
    }
}