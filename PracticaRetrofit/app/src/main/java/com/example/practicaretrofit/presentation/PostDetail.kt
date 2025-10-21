package com.example.practicaretrofit.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.practicaretrofit.presentation.models.CommentUI


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostDetailScreen(postId: Int, onBack:() ->Unit) {
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text("Post Detail")
            }, navigationIcon = {
                IconButton (onClick = {
                    onBack()
                }) {
                    Icon(Icons.Filled.ArrowBack, "")

                }
            })
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            PostDetailContent(postId = postId)
        }
    }
}


@Composable
fun PostDetailContent(postId: Int) {
    val viewModel: PostDetailViewModel = viewModel()

    val postUI by viewModel.post.collectAsState()
    val comments by viewModel.comments.collectAsState()
    val error by viewModel.error.collectAsState()

    LaunchedEffect(postId) {
        viewModel.getPostById(postId)
        viewModel.getCommentsByPostId(postId)
    }

    Column {
        PostItem(postUI) { }


        Text("Comments", modifier = Modifier.padding(vertical = 8.dp))

        LazyColumn {
            items(comments.size) {
                CommentItem(comment = comments[it])
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

    }

    if (error != null){
        AlertDialog(onDismissRequest = {}, confirmButton = {
            Text(
                "OK", modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                        viewModel.dismissError()
                    })
        }, title = {
            Text("Error")
        }, text = {
            Text(error ?: "Unknown error")
        })
    }


}

@Composable
fun CommentItem(comment: CommentUI) {
    Card(Modifier
        .fillMaxSize()
        .padding(8.dp)) {
        Column(Modifier.padding(16.dp)) {
            Text(text = comment.name, style = MaterialTheme.typography.titleSmall)
            Text(text = comment.email, style = MaterialTheme.typography.labelSmall)
            Text(text = comment.body, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

