package com.example.practicaretrofit.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicaretrofit.presentation.models.CommentUI
import com.example.practicaretrofit.presentation.models.PostUI
import com.example.practicaretrofit.repository.PostRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PostDetailViewModel : ViewModel() {

    private var _error: MutableStateFlow<String?> = MutableStateFlow(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    private var _comments: MutableStateFlow<List<CommentUI>> = MutableStateFlow(emptyList())
    val comments = _comments.asStateFlow()


    var _post = MutableStateFlow<PostUI>(PostUI())
    val post = _post.asStateFlow()

    val repository = PostRepository()


    fun getData(postId: Int)  = viewModelScope.launch {
       async {
            val result = repository.getPostById(postId)
            result.fold(
                onSuccess =  {data ->
                    _post.update { data }
                },
                onFailure = {error ->
                    _error.update { error.message}
                }
            )

        }.await()

        async {
            val result = repository.getCommentsById(postId)
            result.fold(
                onSuccess = { data ->
                    _comments.update { data }
                },
                onFailure = { error ->
                    _error.update { error.message }
                }
            )
        }.await()


    }


    fun getPostById(postId:Int) = viewModelScope.launch {
        val result = repository.getPostById(postId)
        result.fold(
            onSuccess =  {data ->
                _post.update { data }
            },
            onFailure = {error ->
                _error.update { error.message}
            }
        )
    }

    fun getCommentsByPostId(postId: Int) = viewModelScope.launch {
        val result = repository.getCommentsById(postId)
        result.fold(
            onSuccess = { data ->
                _comments.update { data }
            },
            onFailure = { error ->
                _error.update { error.message }
            }
        )
    }

    fun dismissError() = viewModelScope.launch {
        _error.update { null }
    }
}