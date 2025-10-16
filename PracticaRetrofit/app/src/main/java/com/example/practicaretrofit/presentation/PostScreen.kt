package com.example.practicaretrofit.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.example.practicaretrofit.presentation.models.PostUI

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostListScreen(
    list: List<PostUI>,
    isLoading: Boolean,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier
) {

    Box(modifier = Modifier.fillMaxSize()) {
        PullToRefreshBox(
            isRefreshing = isLoading,
            onRefresh = onRefresh,
            modifier = modifier
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                if (list.isEmpty()) {
                    item {
                        Box(modifier = Modifier.fillParentMaxSize()) { }
                    }
                } else {
                    items(list) {
                        PostItem(it)
                    }
                }
            }
        }
    }
}


@Composable
fun PostItem(post: PostUI) {
    Card {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(post.primaryTitle, style = MaterialTheme.typography.titleMedium)
            Text(post.description, style = MaterialTheme.typography.bodyMedium)
            Text(post.userId)
            Text(post.id)
        }
    }
}


@PreviewLightDark
@Composable
fun PostItemPreview() {
    MaterialTheme {
        Scaffold { padding ->
            Column(modifier = Modifier.padding(padding)) {
                PostItem(PostUI("1", "1", "Hola", "Mundo"))

            }
        }
    }
}