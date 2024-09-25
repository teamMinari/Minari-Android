package com.nohjason.minari

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter

@Composable
fun NewsFeedScreen() {
    Column {
        // Top App Bar
        TopAppBar(
            title = { Text(text = "For You") },
            actions = {
                IconButton(onClick = { /* TODO */ }) {
                    Icon(Icons.Default.Search, contentDescription = "Search")
                }
            }
        )

        // Tabs
        val tabs = listOf("Follow", "For You", "World", "Sport", "Entert")
        var selectedTabIndex by remember { mutableStateOf(1) }
        TabRow(selectedTabIndex = selectedTabIndex) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title) },
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index }
                )
            }
        }

        // News Feed List
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(newsList) { newsItem ->
                NewsItem(newsItem)
            }
        }
    }
}

@Composable
fun NewsItem(newsItem: NewsItemData) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = newsItem.title,
            style = MaterialTheme.typography.h6,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(4.dp))
        if (newsItem.imageUrl.isNotEmpty()) {
            Image(
                painter = rememberImagePainter(newsItem.imageUrl),
                contentDescription = newsItem.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = newsItem.category)
            Text(text = "${newsItem.comments} Comments")
        }
    }
}

// Dummy data for preview
data class NewsItemData(
    val title: String,
    val imageUrl: String,
    val category: String,
    val comments: Int
)

val newsList = listOf(
    NewsItemData(
        title = "Brexit isn't as clear cut as it might appear...",
        imageUrl = "https://example.com/image1.jpg",
        category = "USA",
        comments = 144
    ),
    NewsItemData(
        title = "UK government launches 'ambitious' air pollution plan",
        imageUrl = "",
        category = "Word",
        comments = 233
    ),
    NewsItemData(
        title = "Divorce could knock Jeff Bezos out of world's richest spot",
        imageUrl = "https://example.com/image2.jpg",
        category = "Entertainment",
        comments = 233
    ),
    // Add more items as needed...
)

@Preview(showBackground = true)
@Composable
fun PreviewNewsFeedScreen() {
    NewsFeedScreen()
}